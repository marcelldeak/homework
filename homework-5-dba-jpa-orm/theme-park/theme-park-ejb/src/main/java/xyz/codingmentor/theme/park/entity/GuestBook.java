package xyz.codingmentor.theme.park.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import xyz.codingmentor.theme.park.dto.GuestBookDTO;

@Entity
public class GuestBook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "theme_park_id")
    private ThemePark themePark;

    @OneToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    @Temporal(TemporalType.DATE)
    private Date dateOfEntry;

    @Size(min = 1, max = 250)
    private String entryText;

    public GuestBook() {
        // default constructor
    }

    public GuestBook(Long id, ThemePark themePark, Visitor visitor, Date dateOfEntry, String entryText) {
        this.id = id;
        this.themePark = themePark;
        this.visitor = visitor;
        this.dateOfEntry = dateOfEntry;
        this.entryText = entryText;
    }

    public GuestBook(GuestBookDTO guestbook){
        this.id = guestbook.getId();
        this.themePark = new ThemePark(guestbook.getThemePark());
        this.visitor = new Visitor(guestbook.getVisitor());
        this.dateOfEntry = guestbook.getDateOfEntry();
        this.entryText = guestbook.getEntryText();
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
