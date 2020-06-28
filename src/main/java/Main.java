import com.easyar.service.app.crs.Crs;
import com.easyar.service.app.crs.CrsResult;
import com.easyar.service.app.pose.Pose;
import com.easyar.service.app.pose.PoseResult;
import com.easyar.service.auth.token.TokenResult;
import com.easyar.service.auth.token.Token;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        // CRS 与 手势/姿态识别, 使用 token 与 签名 访问服务代码实现

        // 请开启相应调试代码及修改参数
//        testCrs();
//        testPose();
    }


    /**
     * 云识别
     */
    public static void testCrs() {
        String apiKey = "******";
        String apiSecret = "******";

        // CRS AppId
        String appId = "******";
        String appUrl = "http://***.cn1.crs.easyar.com:8080/search";

        // 签名方式
        System.out.println("==========CRS Signature==========");
        Crs crsBySignature = new Crs(apiKey, apiSecret, appId, appUrl);
        try {
            CrsResult crsResult = crsBySignature.searchBySignature(getImage());
            if (crsResult.getStatusCode() == 0) {
                System.out.println("搜索成功:");
                System.out.println(crsResult.getResult().getTarget().getTargetId());
            } else {
                System.out.println("搜索失败:");
                System.out.println(crsResult.getResult().getMessage());
            }
        } catch (Exception e) {
            System.out.println("搜索异常:");
            System.out.println(e.getMessage());
        }

        // token方式
        System.out.println("==========CRS Query Token==========");
        TokenResult tokenResult = getToken(apiKey, apiSecret, 3600);
        if (tokenResult == null || tokenResult.getStatusCode() != 0) {
            return;
        }

        System.out.println("==========CRS Signature==========");
        Crs crsByToken = new Crs(appId, appUrl);
        try {
            CrsResult crsResult = crsByToken.searchByToken(tokenResult.getResult().getToken(), getImage());
            if (crsResult.getStatusCode() == 0) {
                System.out.println("搜索成功:");
                System.out.println(crsResult.getResult().getTarget().getTargetId());
            } else {
                System.out.println("搜索失败:");
                System.out.println(crsResult.getResult().getMessage());
            }
        } catch (Exception e) {
            System.out.println("搜索异常:");
            System.out.println(e.getMessage());
        }
    }

    /**
     * 手势/姿态识别
     */
    public static void testPose() {
        String apiKey = "******";
        String apiSecret = "******";

        // 手势: hand; 姿态: body
        String target = "hand";
        // AI AppID
        String appId = "******";

        String appUrl = "https://ai-api.easyar.com:8443/v1/pose/" + target;


        // 签名方式
        System.out.println("==========Pose Signature==========");
        Pose poseBySignature = new Pose(apiKey, apiSecret, appId, appUrl);
        try {
            PoseResult poseResult = poseBySignature.searchBySignature(getImage());
            if (poseResult.getStatusCode() == 0) {
                System.out.println("搜索成功:");
                System.out.println(poseResult.getResult().get(target).getItems());
            } else {
                System.out.println("搜索失败:");
                System.out.println(poseResult.getMessage());
            }
        } catch (Exception e) {
            System.out.println("搜索异常:");
            System.out.println(e.getMessage());
        }

        // token方式
        System.out.println("==========Pose Query Token==========");
        TokenResult tokenResult = getToken(apiKey, apiSecret, 3600);
        if (tokenResult == null || tokenResult.getStatusCode() != 0) {
            return;
        }

        System.out.println("==========Pose Token==========");
        Pose poseByToken = new Pose(appId, appUrl);
        try {
            PoseResult poseResult = poseByToken.searchByToken(tokenResult.getResult().getToken(), getImage());
            if (poseResult.getStatusCode() == 0) {
                System.out.println("搜索成功:");
                System.out.println(poseResult.getResult().get(target).getItems());
            } else {
                System.out.println("搜索失败:");
                System.out.println(poseResult.getMessage());
            }
        } catch (Exception e) {
            System.out.println("搜索异常:");
            System.out.println(e.getMessage());
        }
    }

    public static TokenResult getToken(String apiKey, String apiSecret, int expires) {
        TokenResult tokenResult = null;

        try {
            Token token = new Token();
            tokenResult = token.getToken(apiKey, apiSecret, expires);

            if (tokenResult.getStatusCode() == 0) {
                System.out.println("token信息:");
                System.out.println("security token: " + tokenResult.getResult().getToken());
                System.out.println("expiration date: " + tokenResult.getResult().getExpiration().toInstant().toString());
            } else {
                System.out.println("生成token错误:");
                System.out.println(tokenResult.getMsg());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return tokenResult;
    }

    public static String getImage() {
        String image = "";
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("/path/to/your/image"));
            image = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return image;
    }
}
