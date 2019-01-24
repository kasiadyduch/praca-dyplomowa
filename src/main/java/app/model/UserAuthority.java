package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_authority")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer user_id;
    Integer authority_id;
}
