package sep3.drivia.tier2.dto;

public record CarResponse(
    int id,
    String model,
    String category,
    String location
) {}