//package lk.ijse.userservice.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lk.ijse.userservice.service.UserService;
//import lk.ijse.userservice.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
////    @Autowired
////    private JwtUtil jwtUtil;
////    @Autowired
////    private UserService userService;
////
////    @Override
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////        String authHeader = request.getHeader("Authorization");
////        if(authHeader != null && authHeader.startsWith("Bearer ")) {
////            String token = authHeader.substring(7);
////            String username = jwtUtil.extractUsername(token);
////
////            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////                UserDetails userDetails = userService.loadUserByUserName(username);
////                if(jwtUtil.validateToken(token, userDetails.getUsername())) {
////                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
////                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
////                }
////            }
////        }
////        filterChain.doFilter(request, response);
////    }
//}
