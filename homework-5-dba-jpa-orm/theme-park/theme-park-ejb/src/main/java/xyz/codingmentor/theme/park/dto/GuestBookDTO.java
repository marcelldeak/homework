package xyz.codingmentor.theme.park.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import xyz.codingmentor.theme.park.entity.GuestBook;

public class GuestBookDTO {

    private Long id;

    @NotNull
    private ThemeParkDTO themePark;

    @NotNull
    private VisitorDTO visitor;

    @NotNull
    private Date dateOfEntry;

    @NotNull
    private String entryText;

    public GuestBookDTO() {
        //default constructor
    }

    public GuestBookDTO(Long id, ThemeParkDTO themePark, VisitorDTO visitor, Date dateOfEntry, String entryText) {
        this.id = id;
        this.themePark = themePark;
        this.visitor = visitor;
        this.dateOfEntry = dateOfEntry;
        this.entryText = entryText;
    }

    public GuestBookDTO(GuestBook guestbook) {
        this.id = guestbook.getId();
        this.themePark = new ThemeParkDTO(guestbook.getThemePark());
        this.visitor = new VisitorDTO(guestbook.getVisitor());
        this.dateOfEntry = guestbook.getDateOfEntry();
        this.entryText = guestbook.getEntryText();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ThemeParkDTO getThemePark() {
        return themePark;
    }

    public void setThemePark(ThemeParkDTO themePark) {
        this.themePark = themePark;
    }

    public VisitorDTO getVisitor() {
        return visitor;
    }

    public void setVisitor(VisitorDTO visitor) {
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
        return "GuestBookDTO{" + "id=" + id + ", themePark=" + themePark + ", visitor=" + visitor + ", dateOfEntry=" + dateOfEntry + ", entryText=" + entryText + '}';
    }
}
