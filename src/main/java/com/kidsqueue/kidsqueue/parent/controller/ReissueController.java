package com.kidsqueue.kidsqueue.parent.controller;

import com.kidsqueue.kidsqueue.parent.db.RefreshEntity;
import com.kidsqueue.kidsqueue.parent.db.RefreshRepository;
import com.kidsqueue.kidsqueue.parent.jwt.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class ReissueController {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public ReissueController(JWTUtil jwtUtil, RefreshRepository refreshRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        String refresh = null;
        Cookie[] cookies = request.getCookies(); // 쿠키를 배열로 가져옴
        for (Cookie cookie : cookies) { // 쿠키 배열을 순회해서 refresh라는 key값을 가진 배열에 refreshToken값 저장

            if (cookie.getName().equals("refresh")) {

                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {

            // refreshToken이 없는 경우
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }

        // expired 되었는지 확인하고 예외처리
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {

            //response status code
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // 토큰이 refreshToken인지 확인 (카테고리는 발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {

            // refreshToken 아닌 경우
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        // refreshToken이 DB(refreshRepository)에 저장되어 있는지 확인
        Boolean isExist = refreshRepository.existsByRefresh(refresh);
        if (!isExist) {

            //response body
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        // refreshToken 맞고, 만료되지 않았을 경우 loginId, role 뽑아낸 후 새로운 accessToken을 발급
        String loginId = jwtUtil.getLoginId(refresh);
        String role = jwtUtil.getRole(refresh);

        String newAccess = jwtUtil.createJwt("access", loginId, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", loginId, role, 86400000L);

        // refresh rotate 시 refreshRepository 에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        refreshRepository.deleteByRefresh(refresh);
        addRefreshEntity(loginId, newRefresh, 86400000L);

        response.setHeader("access", newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void addRefreshEntity(String loginId, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setLoginId(loginId);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
