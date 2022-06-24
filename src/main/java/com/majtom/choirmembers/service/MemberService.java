package com.majtom.choirmembers.service;

import com.majtom.choirmembers.domain.Member;
import com.majtom.choirmembers.exception.BadResourceException;
import com.majtom.choirmembers.exception.ResourceAlreadyExistsException;
import com.majtom.choirmembers.exception.ResourceNotFoundException;
import com.majtom.choirmembers.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    private boolean existsById(Long id) {
        return memberRepository.existsById(id);
    }

    public Member findById(Long id) throws ResourceNotFoundException {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            throw new ResourceNotFoundException("Cannot find Member with id: " + id);
        } else return member;
    }

    public List<Member> findAll(int pageNumber, int rowPerPage) {
        List<Member> members = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        memberRepository.findAll(sortedByIdAsc).forEach(members::add);
        return members;
    }

    public Member save(Member member) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(member.getName())) {
            if (member.getId() != null && existsById(member.getId())) {
                throw new ResourceAlreadyExistsException("Member with id: " + member.getId() + " already exists");
            }
            return memberRepository.save(member);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save member");
            exc.addErrorMessage("Member is null or empty");
            throw exc;
        }
    }

    public void update(Member member) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(member.
                getName())) {
            if (!existsById(member.
                    getId())) {
                throw new ResourceNotFoundException("Cannot find Member with id: " + member.getId());
            }
            memberRepository.save(member);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save member");
            exc.addErrorMessage("Member is null or empty");
            throw exc;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find member " + "with id: " + id);
        } else {
            memberRepository.deleteById(id);
        }
    }

    public Long count() {
        return memberRepository.count();
    }

//----------------------------------------------------------------
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    public Iterable<Member> save (List<Member> members) {
//
//       return memberRepository.saveAll(members);
//    }
//    public Member save (Member member) {
//
//        return memberRepository.save(member);
//    }
//
//    private boolean existsById(Long id) {
//
//        return memberRepository.existsById(id);
//
//    }
//    public Member findById(Long id) throws ResourceNotFoundException{
//        Optional<Member> optional = memberRepository.findById(id);
//        Member member = null;
//        if (optional.isPresent())
//            member = optional.get();
//        else
//            throw new RuntimeException("Meber not found for ID :" + id);
//        return member;
//    }
//
//    public Iterable<Member> findAll(){
//        return memberRepository.findAll();
//    }
}
