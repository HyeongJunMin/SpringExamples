package com.hjmin.jpaboard.domain.member;

import com.hjmin.jpaboard.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

  private Email email;
  private Address address;

  public MemberResponseDto(Member member) {
    this.email = member.getEmail();
    this.address = member.getAddress();
  }

}
