package com.gfg.jbdl.library.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminCreateRequest {
    private String name;
    private String email;
    private String password;
}
