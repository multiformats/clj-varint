(ns net.mpare.varint-test
  (:require [clojure.test :refer :all]
            [net.mpare.varint :refer :all]))

(deftest varint-test
  (testing "300 outputstream"
    (let [output   (java.io.ByteArrayOutputStream.)
          expected (seq (byte-array 2 [0xAC 0x02]))]
      (is (= expected (-> (do (write-int output 300) output)
                          (.toByteArray)
                          (seq))))))
  (testing "inputstream <-> outputstream"
    (let [input  (java.io.PipedInputStream.)
          output (java.io.PipedOutputStream.)
          _      (.connect output input)]
      (is (= 300 (do (write-int output 300)
                     (read-int input))))))
  (testing "300 bytebuffer"
    (let [backing  (byte-array 2)
          output   (java.nio.ByteBuffer/wrap backing)
          expected (seq (byte-array 2 [0xAC 0x02]))]
      (is (= expected (-> (do (write-int output 300) backing)
                          seq)))))
  (testing "bytebuffer <-> bytebuffer"
    (let [buffer (java.nio.ByteBuffer/allocate 200)]
      (write-int buffer 300)
      (.flip buffer)
      (is (= 300 (read-int buffer))))))
