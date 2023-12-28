package com.example.week3

import com.example.week3.dto.request.ChargeRequestDto
import com.example.week3.dto.request.OrderRequestDto
import com.example.week3.repository.MemberRepository
import com.example.week3.repository.MenuRepository
import com.example.week3.repository.OrderRepository
import com.example.week3.service.MemberService
import com.example.week3.service.OrderService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
class ConcurrencyTest @Autowired constructor(
    private val menuRepository: MenuRepository,
    private val memberRepository: MemberRepository,
    private val orderRepository: OrderRepository,
    private val orderService: OrderService,
    private val memberService: MemberService
) {
    @BeforeEach
    fun init() {
        memberService.addBalance(
            ChargeRequestDto(chargeAmount = 20000L), 1
        )
    }

    @Test
    fun concurrencyTest1(){
        val orderRequest =
            OrderRequestDto(
                memberId = 1,
                menuList = listOf(1, 2, 3)
            )

        val numberOfThread = 4
        val executor = Executors.newFixedThreadPool(numberOfThread)
        val latch = CountDownLatch(4)

        executor.execute{
            orderService.createOrder(orderRequest)
            latch.countDown()
        }
        executor.execute{
            orderService.createOrder(orderRequest)
            latch.countDown()
        }
        executor.execute{
            orderService.createOrder(orderRequest)
            latch.countDown()
        }
        executor.execute{
            orderService.createOrder(orderRequest)
            latch.countDown()
        }
        latch.await()


        val memberBalance = memberRepository.findById(1).orElseThrow().balance
        val salesOfOne = menuRepository.findById(1).orElseThrow().sales


        assertThat(memberBalance).isEqualTo(8000)
        assertThat(salesOfOne).isEqualTo(4)
        //현재 로직에는 개수를 수정하는 다른 로직이 없기 때문에 (멤버에서 락걸면 어차피 개수수정로직 실행 안됨)


    }

}