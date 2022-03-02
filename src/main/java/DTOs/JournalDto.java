package DTOs;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class JournalDto implements Serializable {
    private final Long id;
    private final LocalDateTime journalAccessedLast;
    private final String pastAilmentsDescribed;

    public JournalDto(Long id, LocalDateTime journalAccessedLast, String pastAilmentsDescribed) {
        this.id = id;
        this.journalAccessedLast = journalAccessedLast;
        this.pastAilmentsDescribed = pastAilmentsDescribed;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getJournalAccessedLast() {
        return journalAccessedLast;
    }

    public String getPastAilmentsDescribed() {
        return pastAilmentsDescribed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalDto entity = (JournalDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.journalAccessedLast, entity.journalAccessedLast) &&
                Objects.equals(this.pastAilmentsDescribed, entity.pastAilmentsDescribed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, journalAccessedLast, pastAilmentsDescribed);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "journalAccessedLast = " + journalAccessedLast + ", " +
                "pastAilmentsDescribed = " + pastAilmentsDescribed + ")";
    }
}
