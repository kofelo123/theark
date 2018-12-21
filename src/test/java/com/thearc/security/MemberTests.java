package com.thearc.security;

import com.thearc.config.RootConfig;
import com.thearc.config.SecurityConfig;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, SecurityConfig.class})
@Log4j
@ActiveProfiles("server")
public class MemberTests {

  @Setter(onMethod_ = @Autowired)
  private PasswordEncoder pwencoder;
  
  @Setter(onMethod_ = @Autowired)
  private DataSource ds;

  @Ignore
  @Test
  public void testInsertMember() {

    String sql = "insert into tbl_member(userid, userpw, username) values (?,?,?)";
    
    for(int i = 0; i < 100; i++) {
      
      Connection con = null;
      PreparedStatement pstmt = null;
      
      try {
        con = ds.getConnection();
        pstmt = con.prepareStatement(sql);
        
        pstmt.setString(2, pwencoder.encode("pw" + i));
        
        if(i <80) {
          
          pstmt.setString(1, "user"+i);
          pstmt.setString(3,"일반사용자"+i);
          
        }else if (i <90) {
          
          pstmt.setString(1, "manager"+i);
          pstmt.setString(3,"운영자"+i);
          
        }else {
          
          pstmt.setString(1, "admin"+i);
          pstmt.setString(3,"관리자"+i);
          
        }
        
        pstmt.executeUpdate();
        
      }catch(Exception e) {
        e.printStackTrace();
      }finally {
        if(pstmt != null) { try { pstmt.close();  } catch(Exception e) {} }
        if(con != null) { try { con.close();  } catch(Exception e) {} }
        
      }
    }//end for
  }
  @Ignore
  @Test
  public void testInsertAuth() {
    
    
    String sql = "insert into tbl_member_auth (userid, auth) values (?,?)";
    
    for(int i = 0; i < 100; i++) {
      
      Connection con = null;
      PreparedStatement pstmt = null;
      
      try {
        con = ds.getConnection();
        pstmt = con.prepareStatement(sql);
      
        
        if(i <80) {
          
          pstmt.setString(1, "user"+i);
          pstmt.setString(2,"ROLE_USER");
          
        }else if (i <90) {
          
          pstmt.setString(1, "manager"+i);
          pstmt.setString(2,"ROLE_MEMBER");
          
        }else {
          
          pstmt.setString(1, "admin"+i);
          pstmt.setString(2,"ROLE_ADMIN");
          
        }
        
        pstmt.executeUpdate();
        
      }catch(Exception e) {
        e.printStackTrace();
      }finally {
        if(pstmt != null) { try { pstmt.close();  } catch(Exception e) {} }
        if(con != null) { try { con.close();  } catch(Exception e) {} }
        
      }
    }//end for
  }

  @Test
  public void testInsertCustom(){

    String sql ="insert into tbl_user(uid,upw,uname,email)values(?,?,?,?)";
//    String sql2 = "insert into tbl_user_auth (uid, authority) values (?,?)";
    Connection con = null;
    PreparedStatement pstmt = null;

    try{
      con = ds.getConnection();
      pstmt = con.prepareStatement(sql);

      pstmt.setString(1,"test_thearc");
      pstmt.setString(2,pwencoder.encode("test_thearc"));
      pstmt.setString(3,"test_thearc");
      pstmt.setString(4,"test_thearc@naver.com");

//      pstmt.setString(1, "admin2");
//      pstmt.setString(2,"ROLE_ADMIN");


      pstmt.executeUpdate();
    }catch(Exception e){
      e.printStackTrace();;
    }finally {
      if(pstmt != null) { try { pstmt.close();  } catch(Exception e) {} }
      if(con != null) { try { con.close();  } catch(Exception e) {} }
    }

  }
  
}


