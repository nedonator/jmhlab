package benchmarks;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class DTO implements Serializable {
    public int id;
    public String name;
    public boolean isLenin;
    public Collection<String> hobbies;

    static ObjectMapper mapper = new ObjectMapper();

    static DTO createDto(){
        DTO dto = new DTO();
        dto.id = 228;
        dto.name = "pavlik";
        dto.isLenin = false;
        dto.hobbies = List.of("video", "substances");
        return dto;
    }

    public static void main(String[] args) throws IOException {
        DTO dto = createDto();
        File file = new File("dto.json");
        FileWriter fileWriter = new FileWriter(file);
        System.out.println(mapper.writeValueAsString(dto));
        fileWriter.write(mapper.writeValueAsString(dto));
        fileWriter.close();
    }
}
