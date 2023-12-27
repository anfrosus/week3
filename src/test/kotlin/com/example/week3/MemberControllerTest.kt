package com.example.week3

import com.example.week3.dto.request.MemberRequestDto
import com.example.week3.dto.response.MemberResponseDto
import com.example.week3.enums.MemberRoleEnum
import com.example.week3.service.MemberService
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest
@AutoConfigureMockMvc//mockMvc 자동주입
@ExtendWith(MockKExtension::class)
class MemberControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) : DescribeSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    val memberService = mockk<MemberService>() //목킹

    describe("POST /members") {
        context("회원을 생성할 때") {
            it("201 status code를 응답한다.") {
                val memberRequest = MemberRequestDto(
                    memberName = "회원이름",
                    password = "비밀번호"
                )

                every { memberService.createMember(memberRequest) } returns MemberResponseDto(
                    memberName = "회원이름",
                    memberRole = MemberRoleEnum.USER.name
                )
                val requestObj = ObjectMapper().writeValueAsString(memberRequest.toString())
                val result = mockMvc.perform(
                    post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestObj)
                ).andReturn()

                result.response.status shouldBe 201
                result.response.contentAsString shouldBe requestObj
            }
        }
    }
})