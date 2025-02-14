enum DafitWatchFaceLayoutType {
  defaultWatchFaceBgMd5("00000000000000000000000000000000"),
  watchFaceTimeTop(0),
  watchFaceTimeBottom(1),
  watchFaceContentClose(0),
  watchFaceContentDate(1),
  watchFaceContentSleep(2),
  watchFaceContentHeartRate(3),
  watchFaceContentStep(4);

  final dynamic value;
  const DafitWatchFaceLayoutType(this.value);
}
