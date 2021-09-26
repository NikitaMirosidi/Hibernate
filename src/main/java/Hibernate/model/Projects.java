package Hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"company", "customer", "developers"})
@EqualsAndHashCode(exclude = {"company", "customer", "developers"})
@Entity(name = "projects")
public class Projects implements BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "cost")
    private int cost;

    @Column(name = "creation_date")
    private String creationDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Companies company;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToMany(mappedBy = "projects")
    private Set<Developers> developers;
}