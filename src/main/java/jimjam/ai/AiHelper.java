package jimjam.ai;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.List;

public class AiHelper {
    private final ChatModel model;

    public AiHelper() {
        this.model = OpenAiChatModel.builder()
                .apiKey(System.getenv("LLM_API_KEY"))
                .baseUrl("https://api.groq.com/openai/v1")
                .modelName("llama-3.3-70b-versatile")
                .build();
    }

    public ChatResponse getAiResponse(String systemPrompt, String userPrompt) {
        ChatRequest req = ChatRequest.builder()
                .messages(List.of(
                        SystemMessage.from(systemPrompt),
                        UserMessage.from(userPrompt)
                        )
                )
                .build();

        return model.chat(req);
    }
}
