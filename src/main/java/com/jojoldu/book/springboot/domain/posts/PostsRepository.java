package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 단순히 인터페이스를 생성 후, JpaRepository<Entity 클래서, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성됨
// @Repository를 추가할 필요도 없음
// Entity 클래스와 기본 Entity Repository는 함께 위치해야 함
// 나중에 프로젝트 규모가 커져 도메인별로 프로젝트를 분리해야 한다면 이때 Entity 클래스와 기본 Repository는 함께 움직여야 하므로 도메인 패키지에서 함께 관리함
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // Spring Data Jpa에서 제공하지 않는 메소드는 쿼리로 작성해도 됨
    // 실제로는 Spring Data Jpa에서 제공하는 기본 메소드만으로 해결할 수 있지만 가독성이 좋으니 이번엔 @Query를 선택해서 사용함
    // 규모가 있는 프로젝트에서의 데이터 조회는 FK의 조인, 복잡한 조건 등으로 인해 이런 Entity 클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용함
    // 대표적 예로 querydsl, jooq, MyBatis 등이 있음
    List<Posts> findAllDesc();
}
