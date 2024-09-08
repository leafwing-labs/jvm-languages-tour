(ns dev.user
  (:import [com.google.zxing BarcodeFormat]
           [com.google.zxing.client.j2se BufferedImageLuminanceSource]
           [com.google.zxing.common HybridBinarizer]
           [com.google.zxing.qrcode QRCodeWriter]))

(comment
  (defn write-qrcode
    "Write a QR code to a file."
    [data file]
    (let [writer (QRCodeWriter.)
          matrix (.encode writer data BarcodeFormat/QR_CODE 200 200)
          image  (MatrixToImageWriter/toBufferedImage matrix)]
      (ImageIO/write image "png" file)))

  :rcf)

