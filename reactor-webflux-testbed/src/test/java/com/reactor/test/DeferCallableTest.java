package com.reactor.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

class DeferCallableTest {

    int data = 99;

    public int getData(String caller) {
        System.out.println("called by " + caller);
        return data;
    }

    @Test
    void deferCallableTest() {

        var just = Mono.just(getData("just"));  // print `called by just`
        var defer = Mono.defer(() -> Mono.just(getData("defer")));
        var callable = Mono.fromCallable(() -> getData("callable"));

        System.out.println("Call test start");

        just.subscribe(d -> System.out.println("just d = " + d));           // print `just d = 99`
        defer.subscribe(d -> System.out.println("defer d = " + d));         // print `called by defer\n defer d = 99`
        callable.subscribe(d -> System.out.println("callable d = " + d));   // print `called by callable\n defer d = 99`

        System.out.println("data value change");
        data = 111;
        just.subscribe(d -> System.out.println("just d = " + d));           // print `just d = 99`
        defer.subscribe(d -> System.out.println("defer d = " + d));         // print `called by defer\n defer d = 111`
        callable.subscribe(d -> System.out.println("callable d = " + d));   // print `called by callable\n defer d = 111`

    }

    Mono<String> externalServiceCall() {
        return Mono.just("Response");
    }

    Mono<String> executeWhenEmpty() {
        System.out.println("Execute When Empty !");
        return Mono.just("Other-data");
    }

    @Test
    void deferSwitchIfEmptyTest() {
        externalServiceCall()
                .switchIfEmpty(executeWhenEmpty())
                .subscribe();   // print 'Execute When Empty !'

        externalServiceCall()
                .switchIfEmpty(Mono.defer(this::executeWhenEmpty))
                .subscribe();   // print nothing
    }

    public Mono<String> responseMonoString1() {
        System.out.println("1: This method returns some string ~!");
        return Mono.just("some");
    }

    public Mono<String> responseMonoString2() {
        System.out.println("2: This method returns some string ~!");
        return Mono.defer(() -> Mono.just("some"));
    }

    public Mono<String> responseMonoString3() {
        System.out.println("3: This method returns some string ~!");
        return Mono.fromCallable(() -> "some");
    }

    public String responseString() {
        System.out.println("4: This method returns some string ~!");
        return "some";
    }

    @Test
    void deferCallableTest2() {
        responseMonoString1()
                .repeat(3)
                .subscribe(d -> System.out.println("d = " + d));

        responseMonoString2()
                .repeat(3)
                .subscribe(d -> System.out.println("d = " + d));

        responseMonoString3()
                .repeat(3)
                .subscribe(d -> System.out.println("d = " + d));

        // lazy
        Mono.defer(() -> responseMonoString1())
                .repeat(3)
                .subscribe(d -> System.out.println("d = " + d));

        Mono.fromCallable(() -> responseString())
                .repeat(3)
                .subscribe(d -> System.out.println("d = " + d));
    }

    @Test
    void deferCallab22leTest2() {

        Mono.fromCallable(() -> responseString())
                .repeat(3)
                .subscribe(d -> System.out.println("d = " + d));
    }

}
