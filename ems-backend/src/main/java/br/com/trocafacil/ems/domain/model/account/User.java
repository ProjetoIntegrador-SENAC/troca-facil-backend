package br.com.trocafacil.ems.domain.model.account;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    @Column(unique = true)
    private String login;

    @Nonnull
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities;

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        if (this.authorities == null){
            Set<SimpleGrantedAuthority> sga = new HashSet<>();
            sga.add(new SimpleGrantedAuthority("ROLE_USER"));
            return sga;
        }

        Set<GrantedAuthority> grantedAuthorities = authorities.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return grantedAuthorities;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}