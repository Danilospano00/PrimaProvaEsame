package org.esame.models;


import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;

/**
 * Contiene le seguenti info:
 * - Nome
 * - Cognome
 * - indirizzo email
 * - password
 * - stato: attivo o annullato
 * - Elenco dei followers
 *
 */
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        if (password!=null && !password.startsWith("$2a$10"))
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.email = email;
    }

    public String getLastNameAndFirstName() {
        return this.lastName.concat(" ").concat(this.firstName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!password.startsWith("$2a$10"))
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }


    

  }
