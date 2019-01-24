package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "booking_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer user_id;
    String patient;
    Date date;
    String description;
    String attachmentpath;
}
