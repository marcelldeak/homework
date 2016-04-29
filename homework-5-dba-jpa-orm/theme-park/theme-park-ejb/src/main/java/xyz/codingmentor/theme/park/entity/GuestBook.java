package xyz.codingmentor.theme.park.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class GuestBook implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "theme_park_id")
    private ThemePark themePark;

    @NotNull
    @OneToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateOfEntry;

    @NotNull
    @Size(min = 1, max = 250)
    private String entryText;

    public GuestBook() {
        // default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ThemePark getThemePark() {
        return themePark;
    }

    public void setThemePark(ThemePark themePark) {
        this.themePark = themePark;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getEntryText() {
        return entryText;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    @Override
    public String toString() {
        return "GuestBook{" + "id=" + id + ", themePark=" + themePark + ", visitor=" + visitor + ", dateOfEntry=" + dateOfEntry + ", entryText=" + entryText + '}';
    }

}
