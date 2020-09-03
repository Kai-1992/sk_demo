
import com.alibaba.fastjson.JSON;
import com.hikvision.portal.points.util.DefaultExcelListener;
import com.hikvision.portal.points.util.EasyExcelUtil;
import com.hikvision.portal.points.vo.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author: shaokai
 */
@Slf4j
public class DemoTest {
    private static String path = "C:\\Users\\shaokai\\Desktop\\test.xlsx";

    public static void main(String[] args) {
		System.out.println("这是EasyExcelUtil测试类");
    }

    /**
     * 同步无模型读（默认读取sheet0,从第2行开始读）
     */
    @Test
    public void method() {
        List<Map<Integer, String>> maps = EasyExcelUtil.syncRead(path);
        String result = JSON.toJSONString(maps);
        System.out.println("result = " + result);
    }

    /**
     * 同步无模型读（默认表头占一行，从第2行开始读）
     */
    @Test
    public void method2() {
        List<Map<Integer, String>> maps = EasyExcelUtil.syncRead(path, 1);
        String result = JSON.toJSONString(maps);
        System.out.println("result = " + result);
    }

    /**
     * 同步无模型读（指定sheet和表头占的行数）
     */
    @Test
    public void method3() {
        try (InputStream is = new FileInputStream(new File(path))) {
            List<Map<Integer, String>> maps = EasyExcelUtil.syncRead(is, 1, 2);
            String result = JSON.toJSONString(maps);
            System.out.println("result = " + result);
        } catch (IOException e) {
            log.error("读取excel失败");
        }
    }

    /**
     * 同步无模型读（指定sheet和表头占的行数）
     */
    @Test
    public void method4() {
        File file = new File(path);
        List<Map<Integer, String>> maps = EasyExcelUtil.syncRead(file, 1, 3);
        String result = JSON.toJSONString(maps);
        System.out.println("result = " + result);
    }

    /**
     * 同步按模型读（默认读取sheet0,从第2行开始读）
     */
    @Test
    public void method5() {
        List<TestVo> ts = EasyExcelUtil.syncReadModel(path, TestVo.class);
        String result = JSON.toJSONString(ts);
        System.out.println("result = " + result);
    }

    /**
     * 同步按模型读（默认读取sheet0,从第2行开始读）
     */
    @Test
    public void method6() {
        List<TestVo> ts = EasyExcelUtil.syncReadModel(path, TestVo.class, 1);
        String result = JSON.toJSONString(ts);
        System.out.println("result = " + result);
    }

    /**
     * 同步按模型读（指定sheet和表头占的行数）
     */
    @Test
    public void method7() {
        try (InputStream is = new FileInputStream(new File(path))) {
            List<TestVo> list = EasyExcelUtil.syncReadModel(is, TestVo.class, 1, 1);
            String result = JSON.toJSONString(list);
            System.out.println("result = " + result);
        } catch (IOException e) {
            log.error("读取excel失败");
        }
    }

    /**
     * 同步按模型读（指定sheet和表头占的行数）
     */
    @Test
    public void method8() {
        File file = new File(path);
        List<TestVo> list = EasyExcelUtil.syncReadModel(file, TestVo.class, 1, 1);
        String result = JSON.toJSONString(list);
        System.out.println("result = " + result);
    }

    /**
     * 同步按模型读（指定sheet和表头占的行数）
     */
    @Test
    public void method9() {
        List<TestVo> list = EasyExcelUtil.syncReadModel(path, TestVo.class, 1, 1);
        String result = JSON.toJSONString(list);
        System.out.println("result = " + result);
    }

    /**
     * 异步无模型读（默认读取sheet0,从第2行开始读）
     */
    @Test
    public void method10() {
        DefaultExcelListener<TestVo> excelListener = new DefaultExcelListener<>();
        EasyExcelUtil.asyncRead(path, excelListener);
    }

    /**
     * 异步无模型读（默认读取sheet0,从第2行开始读）
     */
    @Test
    public void method11() {
        DefaultExcelListener<TestVo> excelListener = new DefaultExcelListener<>();
        EasyExcelUtil.asyncRead(path, excelListener, 1);
    }


}

