package engine.dto;

import java.time.LocalDateTime;

public class CompletionDto {
    private int id;
    private LocalDateTime completedAt;

    public CompletionDto(int id, LocalDateTime completedAt) {
        this.id = id;
        this.completedAt = completedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}