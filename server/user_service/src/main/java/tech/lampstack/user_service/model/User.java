package tech.lampstack.user_service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String email;
    private String password;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String sessionId;


    private Date createdOn;
    private Date updatedOn;

}
