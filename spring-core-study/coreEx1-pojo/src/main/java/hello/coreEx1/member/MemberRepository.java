package hello.coreEx1.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
