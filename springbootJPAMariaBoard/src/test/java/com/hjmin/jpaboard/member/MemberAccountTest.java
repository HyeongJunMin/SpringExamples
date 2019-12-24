package com.hjmin.jpaboard.member;

import com.hjmin.jpaboard.controller.MemberRESTController;
import com.hjmin.jpaboard.domain.Member;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
public class MemberAccountTest {

  @Autowired
  private MemberRESTController memberRESTController;

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(memberRESTController).build();
  }


  //@Test
  public void signupTest() throws Exception {
    Member mem = new Member();

    mockMvc.perform(post("/member/signup").param("name","min").param("pw","pw"));
  }
}
