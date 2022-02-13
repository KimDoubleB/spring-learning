import time
import json
import boto3
import os
import urllib3


def lambda_handler(event, context):
    
    # boto3 client
    client = boto3.client("ec2")
    ssm = boto3.client("ssm")
    
    account = event["account"]
    repository_name = event["detail"]["repository-name"]
    image_name = f"{account}.dkr.ecr.ap-northeast-2.amazonaws.com/{repository_name}"
    tag_name = event["detail"]["image-tag"]
    start_command = f"sh /home/ec2-user/github-action/start.sh {image_name} {tag_name}"
    instanceid = "i-07cb1b0c4659fe5e3"
    
    # command to be executed on instance
    response = ssm.send_command(
        InstanceIds=[instanceid],
        DocumentName="AWS-RunShellScript",
        Parameters={
            "commands": [
                "aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 123220671310.dkr.ecr.ap-northeast-2.amazonaws.com",
                start_command
            ]
        },
    )

    # fetching command id for the output
    command_id = response["Command"]["CommandId"]

    time.sleep(2)

    # fetching command output
    output = ssm.get_command_invocation(CommandId=command_id, InstanceId=instanceid)
    
    # send slack alarm
    if tag_name != "latest":
        if output["ResponseMetadata"]["HTTPStatusCode"] == 200:
            send_deploy_slack_alarm(f"Deploy success 🎉\nContainer image: {image_name}\nImage tag: {tag_name}")
        else:
            error_message = output["StandardErrorContent"]
            send_deploy_slack_alarm(f"Deploy failure 😢\nContainer image: {image_name}\nImage tag: {tag_name}\nError message: {error_message}")
    
    
    return {"statusCode": 200, "body": json.dumps("Done")}


def send_deploy_slack_alarm(text):
    webhook = os.environ['SLACK_WEBHOOK']
    http = urllib3.PoolManager()
    payload = {"text" : text}
    response = http.request('POST',
                        webhook,
                        body = json.dumps(payload).encode('utf-8'),
                        headers = {'Content-Type': 'application/json'},
                        retries = False)