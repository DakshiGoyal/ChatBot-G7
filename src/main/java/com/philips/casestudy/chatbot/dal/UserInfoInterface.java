/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.chatbot.dal;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.philips.casestudy.chatbot.domain.UserInfo;

@Repository
@Transactional
public interface UserInfoInterface extends CrudRepository<UserInfo, Integer>{

  @Modifying
  @Query(value="insert into UserInfo(name,contact_no,email,city) values(:name,:contact_no,:email,:city)", nativeQuery = true)
  int save(@Param("name") String name,@Param("contact_no") BigInteger contactno,@Param("email") String email,@Param("city") String city);

  @Override
  @Query("select user from UserInfo as user")
  List<UserInfo> findAll();

  @Query("select user from UserInfo as user where user.contactno=?1")
  List<UserInfo> findByContactno(BigInteger contactno);


  @Query("select user from UserInfo as user where user.email=?1")
  List<UserInfo> findByEmail(String email);


}
