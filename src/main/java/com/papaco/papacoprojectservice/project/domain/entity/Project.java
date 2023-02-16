package com.papaco.papacoprojectservice.project.domain.entity;

import com.papaco.papacoprojectservice.project.domain.vo.CodeStore;
import com.papaco.papacoprojectservice.project.domain.vo.ProjectDescription;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted = false")
@Entity
public class Project {
    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;
    private Long ownerId;

    @Embedded
    private CodeStore codeStore;

    @Embedded
    private ProjectDescription description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectTechStack> projectTechStacks = new ArrayList<>();

    private boolean finished = Boolean.FALSE;
    private boolean deleted = Boolean.FALSE;

    public Project(UUID id, Long ownerId, CodeStore codeStore, ProjectDescription description) {
        this.id = id;
        this.ownerId = ownerId;
        this.codeStore = codeStore;
        this.description = description;
    }

    public void registerTechStacks(List<TechStack> techStacks) {
        List<ProjectTechStack> projectTechStacks = new ArrayList<>();
        techStacks.forEach(s -> projectTechStacks.add(new ProjectTechStack(this, s)));
        this.projectTechStacks.clear();
        this.projectTechStacks.addAll(projectTechStacks);
    }

    public void changeCodeStore(CodeStore codeStore) {
        this.codeStore = codeStore;
    }

    public void editDescription(ProjectDescription description) {
        this.description = description;
    }

    public String getDescription() {
        return description.getDescription();
    }

    public void finish() {
        this.finished = true;
    }

    public void delete() {
        this.deleted = true;
    }

    public List<TechStack> getTechStacks() {
        return this.projectTechStacks.stream()
                .map(ProjectTechStack::getTechStack)
                .collect(Collectors.toList());
    }
}
