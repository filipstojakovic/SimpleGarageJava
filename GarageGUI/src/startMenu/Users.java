package startMenu;

import java.io.Serializable;
import java.util.Objects;

public class Users implements Serializable // mozda moze bez public
{
    private String username;
    private String password;
    private boolean isAdmin=false;


    public Users(String username, String password, boolean isAdmin)
    {
        this.username=username;
        this.password=password;
        this.isAdmin=isAdmin;
    }

    public Users(String username, String password)
    {
        this.username=username;
        this.password=password;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return username.equals(users.username) &&
                password.equals(users.password);
    }

    @Override
    public String toString()
    {
        return "username: "+username + " password: "+password+" isAdmin: "+isAdmin;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(username, password);
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean isAdmin()
    {
        return isAdmin;
    }
}
