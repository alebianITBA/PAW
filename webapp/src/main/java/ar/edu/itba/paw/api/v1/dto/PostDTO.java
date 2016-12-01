package ar.edu.itba.paw.api.v1.dto;

import ar.edu.itba.paw.models.Post;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement()
public class PostDTO {

  @XmlElement(name = "id")
  private Long id;

  @XmlElement(name = "title")
  private String title;

  @XmlElement(name = "description")
  private String description;

  @XmlElement(name = "user")
  private UserDTO user;

  @XmlElement(name = "created_at")
  private Date created_at;

  public PostDTO() {
  }

  public PostDTO(Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.description = post.getDescription();
    this.user = new UserDTO(post.getUser());
    this.created_at = post.getCreatedAt();
  }

  public static List<PostDTO> fromList(List<Post> posts) {
    return posts.stream().map(p -> new PostDTO(p)).collect(Collectors.toList());
  }

}
