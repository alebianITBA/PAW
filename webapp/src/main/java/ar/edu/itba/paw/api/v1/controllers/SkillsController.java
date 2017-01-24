package ar.edu.itba.paw.api.v1.controllers;

import ar.edu.itba.paw.api.v1.dto.SkillDTO;
import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api/v1/skills")
@Component
public class SkillsController extends ApiController {
  @Autowired
  private SkillService skillService;

  @GET
  public Response index() {
    final List<Skill> allSkills = skillService.all();
    GenericEntity<List<SkillDTO>> list = new GenericEntity<List<SkillDTO>>(SkillDTO.fromList(allSkills)) {
    };
    return ok(list);
  }
}
