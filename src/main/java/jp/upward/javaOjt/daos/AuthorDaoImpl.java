package jp.upward.javaOjt.daos;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import jp.upward.javaOjt.beans.AuthorWithBookBean;
import jp.upward.javaOjt.entities.QAuthor;
import jp.upward.javaOjt.entities.QBook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

  private final JPAQueryFactory jpaQueryFactory;

  public List<AuthorWithBookBean> getAuthorWithBookBeansById(Integer id) {
    return jpaQueryFactory.select(
        Projections.constructor(
          AuthorWithBookBean.class,
          QAuthor.author,
          QBook.book
        )
      )
      .from(QAuthor.author)
      .leftJoin(QBook.book)
      .on(QAuthor.author.id.eq(QBook.book.authorId))
      .where(QAuthor.author.id.eq(id))
      .fetch();
  }
}
