package com.furkan.manager;
import com.furkan.dto.request.UserSaveResquestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.furkan.constants.RestEndPoints.SAVE;
/**
 * name => uniq oolmalıdır. kopyala yapıştır yaparken
 * genellikle insanlar unutuyorlar ve isimleri aynı
 * kullanıyortlar bu nedenle doğru çalşmıyor.
 */
@FeignClient(
        name = "user-profile-service-feign",
        url = "http://localhost:9999/user",
        decode404 = true
)
public class ElasticServiceManager {
}
