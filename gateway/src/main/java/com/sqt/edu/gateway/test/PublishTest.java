package com.sqt.edu.gateway.test;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-14 15:49
 */
public class PublishTest {

    public static void main(String[] args) {
//       MyPublisher myPublisher = new MyPublisher();
//        myPublisher.subscribe(new MySubscriber());
//        Flux.just("Hello", "World").subscribe(System.out::println);
//        Flux.range(1, 100).buffer(20).subscribe(System.out::println);

//        Flux.interval(Duration.of(0, ChronoUnit.SECONDS),
//                Duration.of(1, ChronoUnit.SECONDS))
//                .buffer(Duration.of(5, ChronoUnit.SECONDS)).
//                take(2).toStream().forEach(System.out::println);
        Flux.range(1, 10).map(x -> x*x).subscribe(System.out::println);
    }

    static class P{

    }

    static class MyPublisher implements Publisher{

        @Override
        public void subscribe(Subscriber s) {
            s.onNext(s);
        }
    }
    static class MySubscriber implements Subscriber{

        @Override
        public void onSubscribe(Subscription s) {
            System.err.println("This is onSubscribe()");
        }

        @Override
        public void onNext(Object o) {
            System.err.println("This is onNext()");
        }

        @Override
        public void onError(Throwable t) {
            System.err.println("This is onError()");
        }

        @Override
        public void onComplete() {
            System.err.println("This is onComplete()");
        }
    }
}
