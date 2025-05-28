package jp.upward.javaOjt.daos;

import java.util.List;
import jp.upward.javaOjt.beans.AuthorWithBookBean;

public interface AuthorDao {

  List<AuthorWithBookBean> getAuthorWithBookBeansById(Integer id);
}
