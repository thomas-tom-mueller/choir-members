package com.majtom.choirmembers.repository;

import com.majtom.choirmembers.domain.Member;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminRepository extends PagingAndSortingRepository<Member, Long>, JpaSpecificationExecutor<Member> {
}
