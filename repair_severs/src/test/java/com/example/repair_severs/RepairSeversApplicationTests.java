package com.example.repair_severs; // 文件必须在对应的包路径下

import com.example.repair_severs.util.HandleFile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.apache.http.entity.ContentType;

import java.io.*;
import java.util.List;

import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;

@SpringBootTest
class RepairSeversApplicationTests {

    HandleFile handleFile = new HandleFile();
    @Test
    void contextLoads() {
    }

    @Test
    public void testExel() throws Exception {
        // 指定文件路径
        File file = new File("C:\\Users\\13042\\IdeaProjects\\repair_severs\\src\\main\\java\\com\\example\\repair_severs\\file\\api.xlsx");
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            // 使用 MockMultipartFile 来模拟上传的文件
            MultipartFile multipartFile = new MockMultipartFile("copy" + file.getName(),
                    file.getName(),
                    org.apache.http.entity.ContentType.APPLICATION_OCTET_STREAM.toString(),
                    fileInputStream);
            InputStream inputStream = multipartFile.getInputStream();


            List<List<Object>> list = HandleFile.PraseExcel(inputStream, multipartFile.getOriginalFilename());

            inputStream.close();

            // 输出处理结果
            for (List<Object> row : list) {
                System.out.println(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
