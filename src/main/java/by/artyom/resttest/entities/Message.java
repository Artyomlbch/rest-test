package by.artyom.resttest.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Message {
    @JsonProperty("message_text")
    private final String text;
}
