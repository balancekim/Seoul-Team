package com.green.nowon.security;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.UserDTO;
import com.green.nowon.domain.entity.UserEntity;
import com.green.nowon.domain.entity.UserEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {


    
    private final UserEntityRepository userRepository;
    
    private final PasswordEncoder encoder;
    
    //구글로 부터 받은 userRequest 데이터에 대한 후처리 되는 함수
    //함수종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        //각 서비스에 맞게 정보를 가져옴
        //OAuth2UserInfo는 직접 만든 인터페이스 이고,
        //각 브랜드별로 구현체를 만듬
        OAuth2UserInfo oAuth2UserInfo;
        System.out.println("userRequest.getClientRegistration().getRegistrationId()--->"+userRequest.getClientRegistration().getRegistrationId());
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            System.out.println("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
        } else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")){
            System.out.println("카카오 로그인 요청");
            oAuth2UserInfo = new KakaoUserInfo((Map)oAuth2User.getAttributes(), null);
        }else {
            
            return null;
        }

        String provider = oAuth2UserInfo.getProvider(); // google
        String providerId = oAuth2UserInfo.getProviderId();
        String username = oAuth2UserInfo.getName();
        String email = oAuth2UserInfo.getEmail();
        String role = "ROLE_USER";
        String[] emailSplit=email.split("@");
        String nickName=emailSplit[0]+"("+provider+")";
        
        UserEntity un = UserEntity.builder()
                .userId(nickName)
                .email(email)
                .password("1111")
                .userName(username)
                .liveIn(provider)
                .build();
        
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            System.out.println(provider + " 로그인이 최초입니다.");
            
            userRepository.save(UserDTO.toUserDTO(un).toEntity(encoder));
        } else {
            System.out.println(provider +" 로그인을 이미 한 적이 있습니다.");
        }

        return new CustomDetails(un,oAuth2User.getAttributes());
    }
}