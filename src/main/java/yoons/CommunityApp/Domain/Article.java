package yoons.CommunityApp.Domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Entity
@ToString(callSuper = true)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    @Setter
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false)
    private UserAccount userAccount;

    protected Article() {
    }

    private Article(Long id, String title, String content, UserAccount userAccount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userAccount = userAccount;
    }

    public Article of(Long id, String title, String content, UserAccount userAccount){
        return new Article(id,title,content,userAccount);
    }
}
