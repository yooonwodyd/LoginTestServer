package yoons.CommunityApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yoons.CommunityApp.domain.Article;

@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long>{

}