(ns net.mpare.varint
  (:import net.mpare.varint.VarInt

           java.io.InputStream
           java.io.OutputStream
           java.nio.ByteBuffer))

(defprotocol WriteVarInt
  (write-int [this value]))

(defprotocol ReadVarInt
  (read-int [this]))

(extend-type OutputStream
  WriteVarInt
  (write-int [this value] (VarInt/putVarInt value this)))

(extend-type InputStream
  ReadVarInt
  (read-int [this] (VarInt/getVarInt this)))

(extend-type ByteBuffer
  WriteVarInt
  (write-int [this value] (VarInt/putVarInt value this))

  ReadVarInt
  (read-int [this] (VarInt/getVarInt this)))
