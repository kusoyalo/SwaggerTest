package kuku.controller;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "TokenController",description = "Token的控制器")
public class TokenController{
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "創立Token")})
	@RequestMapping(value = "/createToken",method = RequestMethod.POST,consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Map<String,String>> createToken(@Parameter(description = "儲存的資料") @RequestParam("claimMap") Map<String,String> claimMap){
		
		Map<String,String> returnMap = new HashMap<String,String>();
		returnMap.put("result","FALSE");
		
		try{
			//加密方式與Token類型
			Map<String,Object> headerMap = new HashMap<String,Object>();
			headerMap.put("typ","JWT");
			headerMap.put("alg","HS256");
			
			//儲存的資料
			Claims claims = Jwts.claims();
			claims.putAll(claimMap);
			
		    JwtBuilder jwtBuilder = Jwts.builder();
		    jwtBuilder.setHeader(headerMap);
		    
		    Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		    jwtBuilder.signWith(secretKey);
		    
		    jwtBuilder.setClaims(claims);
		    
			String token = jwtBuilder.compact();
			//私鑰不能洩露，這裡是為了展示功能
			String secretKeyString = Encoders.BASE64.encode(secretKey.getEncoded());
			
			returnMap.put("result","TRUE");
			returnMap.put("token",token);
			returnMap.put("secretKeyString",secretKeyString);
		}
		catch(Exception e){
			e.printStackTrace();
			
			returnMap.put("message",e.getMessage());
		}
		
		return ResponseEntity.ok(returnMap);
	}
	@SecurityRequirement(name = "bearerAuth")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "驗證Token")})
	@RequestMapping(value = "/verifyToken",method = RequestMethod.POST,consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Map<String,String>> verifyToken(@Parameter(description = "Token") @RequestParam("token") String token,
			@Parameter(description = "私鑰字串") @RequestParam("secretKeyString") String secretKeyString){
		
		Map<String,String> returnMap = new HashMap<String,String>();
		returnMap.put("result","FALSE");
		
		try{
			Key secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeyString));
			
			JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder();
			jwtParserBuilder.setSigningKey(secretKey);
			JwtParser jwtParser = jwtParserBuilder.build();
			
			Claims body = jwtParser.parseClaimsJws(token).getBody();
			
			for(String key : body.keySet()){
				returnMap.put(key,String.valueOf(body.get(key)));
			}
			
			returnMap.put("result","TRUE");
		}
		catch(Exception e){
			e.printStackTrace();
			
			returnMap.put("message",e.getMessage());
		}
		
		return ResponseEntity.ok(returnMap);
	}
}