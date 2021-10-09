package Hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"companies", "projects"})
@EqualsAndHashCode(exclude = {"companies", "projects"})
@Entity
@Table(name = "customer")
public class Customer implements BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @ManyToMany(mappedBy = "customers")
    private Set<Company> companies;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> projects;
}