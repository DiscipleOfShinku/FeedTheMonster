package feedthemonster;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Monster
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer level;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    public Monster()
    {
        // this form used by Hibernate
    }

    public Monster(String name, Integer level, Date birthday)
    {
        // for application use
        this.name = name;
        this.level = level;
        this.birthday = birthday;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public String getPicture()
    {
        return "/resources/images/" + this.id + ".png";
    }

    public void incrementLevel()
    {
        this.level++;
    }
}
