package com.quyvx.main_server.infrastructure.jpa_repositories;

import com.quyvx.main_server.api.application.models.auth.MemberRole;
import com.quyvx.main_server.api.application.models.member.MemberInfo;
import com.quyvx.main_server.infrastructure.entities.MemberEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberJpaRepository extends BaseJpaRepository<MemberEntity, Long> {

    @Query(value = "SELECT r.type AS type, r.name AS name " +
            "FROM identity_role AS ir " +
            "LEFT JOIN roles AS r ON ir.role_id = r.id " +
            "LEFT JOIN  members AS m ON ir.identity_id = m.identity_id " +
            "WHERE m.id = :memberId ", nativeQuery = true)
    List<MemberRole> findMemberRolesByMemberId(@Param("memberId") Long memberId);


    @Query(value = "SELECT m.id as memberId, m.identity_id as identityId, m.first_name as firstName, " +
            "m.last_name as lastName, m.date_of_birth as birthday, m.post_code as postCode, " +
            "m.address as address, m.email as email, m.phone_number as phone " +
            "FROM  members as m " +
            "WHERE m.id = :memberId ", nativeQuery = true)
    Optional<MemberInfo> getMemberInfoByMemberId(@Param("memberId") Long memberId);
}
