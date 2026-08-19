package com.maria.pet_management_app.Schedule;


import com.maria.pet_management_app.Pet.Pet;
import com.maria.pet_management_app.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="scheduledItems")
@Getter
@Setter
@NoArgsConstructor //required by jpa
@AllArgsConstructor //can cause error bc of order of arguments in contructor
@Builder //automatically generates a package-private all-arguments constructor
public class ScheduledItem {

    @Id
    //id incremented
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long id;


    @Column(nullable = false)
    public String taskName;

//    private String frequency;

    @Column(nullable = false)
    public LocalTime scheduledTime;

    //only loads object when called by getter method
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", nullable = false)
    private Pet pet;









}
