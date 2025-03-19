import Flutter
import CRPSmartBand


public class DafitSdkPlugin: NSObject, FlutterPlugin, CRPManagerDelegate {
    
    // Event sinks for various event channels
    var deviceDataReceivedSink: FlutterEventSink?
    var connectionStateSink: FlutterEventSink?
    var batteryLevelSink: FlutterEventSink?
    var stockChangeSink: FlutterEventSink?
    var quickResponsesSink: FlutterEventSink?
    var breathRateSink: FlutterEventSink?
    var hrvSink: FlutterEventSink?
    var tempSink: FlutterEventSink?
    var movementStateSink: FlutterEventSink?
    var trainingSink: FlutterEventSink?
    var sleepActionSink: FlutterEventSink?
    var stepsCategorySink: FlutterEventSink?
    var stepChangeSink: FlutterEventSink?
    var sleepChangeSink: FlutterEventSink?
    var heartRateSink: FlutterEventSink?
    var bloodPressureSink: FlutterEventSink?
    var bloodOxygenSink: FlutterEventSink?
    var ecgSink: FlutterEventSink?
    var contactsSink: FlutterEventSink?
    var findPhonesSink: FlutterEventSink?
    var cameraSink: FlutterEventSink?
    var onLoadingSink: FlutterEventSink?

    // Event channel names
    static let deviceDataReceivedChannelName = "deviceDataReceived"
    static let connectionStateChannelName = "dafitConnectionState"
    static let batteryLevelChannelName = "dafitGetBatteryLevel"
    static let stockChangeChannelName = "dafitGetStockChange"
    static let quickResponsesChannelName = "dafitGetQuickResponses"
    static let breathRateChannelName = "dafitGetBreathRate"
    static let hrvChannelName = "dafitGetHrv"
    static let tempChannelName = "dafitGetTemp"
    static let movementStateChannelName = "dafitGetMovementState"
    static let trainingChannelName = "dafitGetTraining"
    static let sleepActionChannelName = "dafitGetSleepAction"
    static let stepsCategoryChannelName = "dafitGetStepsCategory"
    static let stepChangeChannelName = "dafitGetStepChange"
    static let sleepChangeChannelName = "dafitGetSleepChange"
    static let heartRateChannelName = "dafitGetHeartRate"
    static let bloodPressureChannelName = "dafitGetBloodPressure"
    static let bloodOxygenChannelName = "dafitGetBloodOxygen"
    static let ecgChannelName = "dafitGetECG"
    static let contactsChannelName = "dafitGetContact"
    static let findPhonesChannelName = "dafitGetFindPhone"
    static let cameraChannelName = "dafitCamera"
    static let onLoadingChannelName = "dafitOnLoading"

    public func receiveStress(_ stress: Int) {
        print("receiveStress =\(stress)")
    }

    public func receiveHRV(_ hrv: Int) {
        print("hrv =\(hrv)")
    }


    public func receiveStockRequestUpdateInfo() {
        print("receiveStockRequestUpdateInfo")
    }

    public func recevieTakePhoto() {
        print("recevieTakePhoto")
    }


    public func receiveUpgradeScreen(_ state: CRPUpgradeState, _ progress: Int) {
        print("state = \(state.description()), progress = \(progress)")

    }

    public func receiveRealTimeHeartRate(_ heartRate: Int, _ rri: Int) {
        print("heart rate is \(heartRate)")
    }
    public func receiveUpgrede(_ state: CRPUpgradeState, _ progress: Int) {
        print("state = \(state.description()), progress = \(progress)")
    }
    public func recevieWeather() {
        print("recevieWeather")
    }

    public func receiveGPTState(type: CRPGPTType, state: CRPGPTRequestState, result: NSData) {
        NSLog("type: \(type.rawValue), state: \(state.rawValue), result: \(result.count)")

        switch state{
        case .ready:
            CRPSmartBandSDK.sharedInstance.setGPTState(type: type, state: .ready)
        case .cancel:
            break
        case .parseFail:
            break
        case .parseSuccess:
            break
        @unknown default:
            print("receiveGPTState unknown")
        }
    }

    public func receiveRequestGPTPreviewImage() {

    }

    public func receiveRequestGPTImage() {

    }



    //Local upgrade file address
    var path = ""

    var verStr = ""
    var macStr = ""
    var mcu: Int? = 0
    var upgradefileDownUrl: String? = ""


    public typealias stepHandler = ((_ step: Int) -> Void)

    var myStepHandler:stepHandler!

    public func getStep(_ handler: @escaping stepHandler) {
        myStepHandler = handler
    }

    public func didState(_ state: CRPState) {
        print("Connect state: \(state.rawValue)")
        if state == .connected{
            CRPSmartBandSDK.sharedInstance.checkDFUState { (dfu, err) in
                print("dfu =\(dfu)")
            }
        }
    }

    public func didBluetoothState(_ state: CRPBluetoothState) {
        print("Bluetooth state \(state)")
    }

    public func receiveSteps(_ model: StepModel) {
        print("Latest steps: \(model.steps)")
    }
    public func receiveHeartRate(_ heartRate: Int) {
        print("Latest heart rate: \(heartRate)")
    }

    public func receiveHeartRateAll(_ model: HeartModel) {
        print("receiveHeartRateAll =\(model.starttime)")
    }

    public func receiveBloodPressure(_ heartRate: Int, _ sbp: Int, _ dbp: Int) {
        print("BP: \(heartRate), \(sbp), \(dbp)")
    }
    public func receiveSpO2(_ o2:Int){
        print("SpO2 = \(o2)")
    }

    public func receiveCalling() {
        print("receiveCalling")
    }


    public func receiveSportState(_ state: SportType, _ err: Int) {
        print("receiveSportState state = \(state), err =\(err)")
    }

    var discoverys = [CRPDiscovery]()
    var myDiscovery:CRPDiscovery!
    var mac = ""
    var contactProfile: contactProfileModel!
    var imageSize: ScreenImageSize!
    var compressionType: Int!


    //:MARK 扫描
    @IBAction public func scan() {

        CRPSmartBandSDK.sharedInstance.scan(10, progressHandler: { (newDiscoverys) in
            let p = newDiscoverys[0]
            self.discoverys.append(p)
//            print("p.ver = \(p.ver) mac =\(String(describing: p.mac))")
//            if (self.mac == "") {
//                if (p.localName?.contains("F605"))! {
//                    self.mac = p.mac!
//                    self.myDiscovery = p
//                    self.stop()
//
//                }
//            }
//            else if ((p.mac?.contains(self.mac))!) {
//                self.myDiscovery = p
//                self.stop()
//                print("Scanned to:\(self.mac)，Please Bind")
//            }
        }) { (newDiscoverys, err) in
            print("error = \(err)")
            print("ok")
        }

    }


    //:MARK 停止扫描
    @IBAction public func stop() {

        CRPSmartBandSDK.sharedInstance.interruptScan()
    }


    //:MARK 绑定
    @IBAction public func bind() {

        if (self.myDiscovery != nil) {
            print("connect ke \(mac)")
            CRPSmartBandSDK.sharedInstance.connet(self.myDiscovery)
        }
        else {
            print("Can not find \(mac)")
        }
    }


    //:MARK 解除绑定
    @IBAction public func unbind() {
        CRPSmartBandSDK.sharedInstance.remove { (state, err) in
            print("Unbind finish")
        }
    }

    //:MARK 断开连接
    @IBAction public func disconnect(_ sender: UIButton) {
        CRPSmartBandSDK.sharedInstance.disConnet()
    }

    //:MARK 重新连接
    @IBAction public func reconnect(_ sender: UIButton) {
        CRPSmartBandSDK.sharedInstance.reConnet()
    }



  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "dafit_sdk", binaryMessenger: registrar.messenger())
    let instance = DafitSdkPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
    CRPSmartBandSDK.sharedInstance.delegate = instance

      // Register event channels
      let deviceDataReceivedHandler = DeviceDataReceivedStreamHandler(plugin: instance)
      let deviceDataReceivedChannel = FlutterEventChannel(name: deviceDataReceivedChannelName, binaryMessenger: registrar.messenger())
      deviceDataReceivedChannel.setStreamHandler(deviceDataReceivedHandler)

      let connectionStateHandler = ConnectionStateStreamHandler(plugin: instance)
      let connectionStateChannel = FlutterEventChannel(name: connectionStateChannelName, binaryMessenger: registrar.messenger())
      connectionStateChannel.setStreamHandler(connectionStateHandler)

      let batteryLevelHandler = BatteryLevelStreamHandler(plugin: instance)
      let batteryLevelChannel = FlutterEventChannel(name: batteryLevelChannelName, binaryMessenger: registrar.messenger())
      batteryLevelChannel.setStreamHandler(batteryLevelHandler)

      let stockChangeHandler = StockChangeStreamHandler(plugin: instance)
      let stockChangeChannel = FlutterEventChannel(name: stockChangeChannelName, binaryMessenger: registrar.messenger())
      stockChangeChannel.setStreamHandler(stockChangeHandler)

      let quickResponsesHandler = QuickResponsesStreamHandler(plugin: instance)
      let quickResponsesChannel = FlutterEventChannel(name: quickResponsesChannelName, binaryMessenger: registrar.messenger())
      quickResponsesChannel.setStreamHandler(quickResponsesHandler)

      let breathRateHandler = BreathRateStreamHandler(plugin: instance)
      let breathRateChannel = FlutterEventChannel(name: breathRateChannelName, binaryMessenger: registrar.messenger())
      breathRateChannel.setStreamHandler(breathRateHandler)

      let hrvHandler = HrvStreamHandler(plugin: instance)
      let hrvChannel = FlutterEventChannel(name: hrvChannelName, binaryMessenger: registrar.messenger())
      hrvChannel.setStreamHandler(hrvHandler)

      let tempHandler = TempStreamHandler(plugin: instance)
      let tempChannel = FlutterEventChannel(name: tempChannelName, binaryMessenger: registrar.messenger())
      tempChannel.setStreamHandler(tempHandler)

      let movementStateHandler = MovementStateStreamHandler(plugin: instance)
      let movementStateChannel = FlutterEventChannel(name: movementStateChannelName, binaryMessenger: registrar.messenger())
      movementStateChannel.setStreamHandler(movementStateHandler)

      let trainingHandler = TrainingStreamHandler(plugin: instance)
      let trainingChannel = FlutterEventChannel(name: trainingChannelName, binaryMessenger: registrar.messenger())
      trainingChannel.setStreamHandler(trainingHandler)

      let sleepActionHandler = SleepActionStreamHandler(plugin: instance)
      let sleepActionChannel = FlutterEventChannel(name: sleepActionChannelName, binaryMessenger: registrar.messenger())
      sleepActionChannel.setStreamHandler(sleepActionHandler)

      let stepsCategoryHandler = StepsCategoryStreamHandler(plugin: instance)
      let stepsCategoryChannel = FlutterEventChannel(name: stepsCategoryChannelName, binaryMessenger: registrar.messenger())
      stepsCategoryChannel.setStreamHandler(stepsCategoryHandler)

      let stepChangeHandler = StepChangeStreamHandler(plugin: instance)
      let stepChangeChannel = FlutterEventChannel(name: stepChangeChannelName, binaryMessenger: registrar.messenger())
      stepChangeChannel.setStreamHandler(stepChangeHandler)

      let sleepChangeHandler = SleepChangeStreamHandler(plugin: instance)
      let sleepChangeChannel = FlutterEventChannel(name: sleepChangeChannelName, binaryMessenger: registrar.messenger())
      sleepChangeChannel.setStreamHandler(sleepChangeHandler)

      let heartRateHandler = HeartRateStreamHandler(plugin: instance)
      let heartRateChannel = FlutterEventChannel(name: heartRateChannelName, binaryMessenger: registrar.messenger())
      heartRateChannel.setStreamHandler(heartRateHandler)

      let bloodPressureHandler = BloodPressureStreamHandler(plugin: instance)
      let bloodPressureChannel = FlutterEventChannel(name: bloodPressureChannelName, binaryMessenger: registrar.messenger())
      bloodPressureChannel.setStreamHandler(bloodPressureHandler)

      let bloodOxygenHandler = BloodOxygenStreamHandler(plugin: instance)
      let bloodOxygenChannel = FlutterEventChannel(name: bloodOxygenChannelName, binaryMessenger: registrar.messenger())
      bloodOxygenChannel.setStreamHandler(bloodOxygenHandler)

      let ecgHandler = ECGStreamHandler(plugin: instance)
      let ecgChannel = FlutterEventChannel(name: ecgChannelName, binaryMessenger: registrar.messenger())
      ecgChannel.setStreamHandler(ecgHandler)

      let contactsHandler = ContactsStreamHandler(plugin: instance)
      let contactsChannel = FlutterEventChannel(name: contactsChannelName, binaryMessenger: registrar.messenger())
      contactsChannel.setStreamHandler(contactsHandler)

      let findPhonesHandler = FindPhonesStreamHandler(plugin: instance)
      let findPhonesChannel = FlutterEventChannel(name: findPhonesChannelName, binaryMessenger: registrar.messenger())
      findPhonesChannel.setStreamHandler(findPhonesHandler)

      let cameraHandler = CameraStreamHandler(plugin: instance)
      let cameraChannel = FlutterEventChannel(name: cameraChannelName, binaryMessenger: registrar.messenger())
      cameraChannel.setStreamHandler(cameraHandler)

      let onLoadingHandler = OnLoadingStreamHandler(plugin: instance)
      let onLoadingChannel = FlutterEventChannel(name: onLoadingChannelName, binaryMessenger: registrar.messenger())
      onLoadingChannel.setStreamHandler(onLoadingHandler)
  }


  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
      let manager = CRPSmartBandSDK.sharedInstance
      let args = call.arguments as? [String: Any]

      switch call.method {
        case "connect":
        let macAddress = args?["macAddress"] as? String ?? ""
          self.scan();
          DispatchQueue.main.asyncAfter(deadline: .now() + 3) { [self] in
              print("Ini setelah 3 detik")
              self.stop()
              for disc in self.discoverys{
                  if disc.mac == macAddress {
                      print("sedang connect")
                      self.myDiscovery = disc
                      self.bind()
                  }
              }
          }
        case "disconnect" :
          print("disconnect")

        case "isConnected" :
          print("isConnected")

        case  "createBond" :
          print("createBond")

        case "10":
            manager.getSteps({ (model, error) in
                print(model)
                print("\(model.steps)step \(model.calory)kcal \(model.distance)m , \(model.time)s")
            })
        case "11":
            manager.getSleepData({ (model, error) in
                print(model)
                print("Deep sleep\(model.deep)Minute Light sleep\(model.light)Minute")
            })
        case "100":
            manager.getFeatures({ (features, error) in
                print("Support：\(features)")
            })

        case "101":
            manager.getSoftver({ (ver, error) in
                print(error)
                print("varsion：\(ver)")
            })
        case "102":
            manager.getBattery({ (battery, error) in
                print("Battery：\(battery)")
            })
        case "103":
            manager.getGoal({ (value, error) in
                print("Goal：\(value)")
            })
        case "104":
            manager.getDominantHand({ (value, error) in
                print("Hand：\(value)")
            })
        case "106":
            manager.getAlarms({ (alarms, error) in
                print(alarms)
            })
        case "107":
            manager.getProfile({ (profile, error) in
                print("profile：\(profile)")
            })
        case "108":
            manager.getLanguage { (value, CRPErrorerror) in
                print("getLanguage：\(value)")
            } _: { (indexs, error) in
                print("index = \(indexs)")
            }

        case "109":
            manager.getDial({ (value, error) in
                print("getDial：\(value)")
            })
        case "110":
            manager.getRemindersToMove({ (value, error) in
                print("getRemindersToMove：\(value)")
            })
        case "111":
            manager.getQuickView({ (value, error) in
                print("getQuickView：\(value)")
            })
        case "112":
            manager.getUnit({ (value, error) in
                print("getUnit：\(value)")
            })
        case "113":
            manager.getTimeformat({ (value, error) in
                print("getTimeformat：\(value)")
            })
        case "114":
            manager.getMac({ (value, error) in
                print("getMac：\(value)")
            })
        case "115":
            manager.getNotifications({ (value, error) in
                print("getNotifications：\(value)")
            })
        case "116":
            manager.getNotifications({ (value, error) in
                print("getNotifications：\(value)")
            })
        case "117":
            manager.setStartSpO2()

        case "118":
            manager.setStopSpO2()

        case "119":

            ///Step1
            manager.getSoftver { (ver, err) in
                self.verStr = ver
            }

            manager.getMac { (mac, err)in
                self.macStr = mac
            }

            ///After Step1 ->Step2:
            /// Get the new version firmware from our server
//            manager.checkLatest(verStr, macStr) { (newVersionInfo, newVersionTpInfo,err ) in
//                self.mcu = newVersionInfo?.mcu
//                self.upgradefileDownUrl = newVersionInfo?.fileUrl
//            }
            ///Step3: Use upgradefileDownUrl to download the file

        case "120":
            /*
             chip: Nordic;Hunter;RealTek;Goodix

             Hunter: Mcu = 4/8/9
             ReakTek: Mcu = 7/11/12/71/72
             Goodix : Mcu = 10
             Nordic: Mcu = Other
             */
            if mcu != nil {
                switch mcu {
                case 4,8,9:
                    manager.getOTAMac { (otaMac, err) in
                        manager.startOTAFromFile(mac: otaMac, zipFilePath: self.path, isUser: false)
                    }
                case 7,11,12,71,72:
                    manager.startRealTekUpgradeFromFile(path: path, timeoutInterval: 30)
                case 10:
                    manager.startGoodixUpgradeFromFile(zipPath: path)
                default:
                    manager.startUpgradeFromFile(path: path)
                }
            }

            //++++++++++++++++++++++++++

        case "90":
            manager.setFindDevice()

        //设置用户资料
        case "300":
            let model = ProfileModel(height: 190, weight: 90, age:-30, gender:.male)
            manager.setProfile(model)
        case "301":
            manager.getProfile { (profile, err) in
                print("profile.height = \(profile.height), progile.weight = \(profile.weight),profile.age = \(profile.age), gender =\(profile.gender)")
            }

        //翻腕亮屏
        case "310":
            manager.setQuickView(false)
        case "311":
            manager.setQuickView(true)

        //时间格式:
        case "320":
            manager.setTimeFormat(0)
        case "121":
            manager.setTimeFormat(1)

        //左右手:
        case "150":
            manager.setDominantHand(0)
        case "151":
            manager.setDominantHand(1)

        //表盘:
        case "160":
            manager.setDial(1)
        case "161":
            manager.setDial(2)
        case "162":
            manager.setDial(3)


        //单位:
        case "170":
            manager.setUnit(0)
        case "171":
            manager.setUnit(1)


        //久坐提醒:
        case "180":
            manager.setRemindersToMove(true)
        case "181":
            manager.setRemindersToMove(false)


        //心率监测:
        case "190":
//            manager.setStopHeart()
            manager.setStopSingleHR()
        case "191":
//            manager.setStartHeart()
            manager.setStartSingleHR()


        //血压
        case "200":
            manager.setStopBlood()
        case "201":
            manager.setStartBlood()
        case "202":
            manager.setCalibrationBlood(88, 110, 88)

        //消息推送::
        case "210":
            manager.setNotification([])
            break
        case "211":
            let swis = [NotificationType.phone, NotificationType.messages, NotificationType.qq, NotificationType.others]
            manager.setNotification(swis)
            break


        //设置目标:
        case "220":
            manager.setGoal(500)
        case "221":
            manager.setGoal(1000)


        //设置语言:
        case "230":
            manager.setLanguage(0)
        case "231":
            manager.setLanguage(1)


        //设置闹钟:
        case "240":
            let alarm = AlarmModel(id: 0, enable: 1, type: .weekly, hour: 12, minute: 55, year: 2017, month: 5, day: 24, weekday: [])
            manager.setAlarm(alarm)
        case "241":
            let alarm1 = AlarmModel(id: 0, enable: 1, type: .weekly, hour: 12, minute: 55, year: 2017, month: 5, day: 24, weekday: [])
            let alarm2 = AlarmModel(id: 1, enable: 1, type: .once, hour: 12, minute: 30, year: 2017, month: 5, day: 24, weekday: [])
            let alarm3 = AlarmModel(id: 2, enable: 1, type: .everyday, hour: 12, minute: 35, year: 2017, month: 5, day: 24, weekday: [])
            manager.setAlarm(alarm1)
            manager.setAlarm(alarm2)
            manager.setAlarm(alarm3)

        //设置24小时心率间隔
        case "250":
            manager.set24HourHeartRate(0)
        case "251":
            manager.set24HourHeartRate(1)
        case "252":
            manager.set24HourHeartRate(2)

        //获取24小时心率间隔
        case "260":
            manager.get24HourHeartRateInterval({ (interval, error) in
                print(interval)
                //sender.setTitle("获取间隔(\(interval))", for: UIControUIControl.State
            })
        //获取当天24小时心率
        case "261":
            manager.get24HourHeartRate({ (hearts, error) in
                print("today heart.count = \(hearts.count), heart = \(hearts)")
            })
//            manager.getFullDayHeartRate({ (hearts, error) in
//                print(hearts)
//            })
        //获取昨天24小时心率
        case "262":
            manager.getAgo24HourHeartRate({ (hearts, error) in
                print("yesterday heart.count = \(hearts.count), heart = \(hearts)")
            })

        case "270":
            manager.getWatchFaceSupportModel { (model, error) in
                print("currentID = \(model.currentID)")
                print("supportModel = \(model.supportModel)")
            }

        case "271":
//            if let pathStr = Bundle.main.path(forResource: "watchFace2", ofType: "bin"){
//                manager.startChangeWathcFaceFromFile(path: pathStr)
//            }
            var currentPage = 1
            var perPage = 18
            getWatchInfo(model: [33], currentPage: currentPage, perPage: perPage)
            break

        case "280":
//            manager.fatigueReminder()
            manager.getWatchFaceInfoByID(11) { (infos, total, count, err) in
                print("infos.count = \(infos.count). total =\(total), count = \(count)")
                for info in infos{
                    print("info.file = \(info.fileUrl), info.image = \(info.imageUrl)")
                }

            }
            break

        case "290":
            manager.get24HourSteps { (steps, error) in
                print("Today steps =\(steps)")
            }
        case "291":
            manager.getAgo24HourSteps { (steps, error) in
                print("Yesterdays steps =\(steps)")
            }
        case "400":
            manager.getPhysiological { (phy, error) in
                print("getPhysiological = \(phy.reminderModels),\(phy.cycleTime),\(phy.menstruationTime),\(phy.lastTimeMonth),\(phy.lastTimeDay),\(phy.remindTimeHour),\(phy.remindTimeMinute)")
            }
        case "401":
            let physiological = Physiological(reminderModels: [reminderModel.menstruation.rawValue,reminderModel.ovulation.rawValue], cycleTime: 24, menstruationTime: 7, lastTimeMonth: 8, lastTimeDay: 3, remindTimeHour: 12, remindTimeMinute: 15)
            manager.setPhysiological(physiological)

        case "410":
            manager.getContactProfile { (model, error) in
                print("model.max = \(model.contactMax), model.width =\(model.contactAvatarWidth), model.height = \(model.contactAvatarHeight)")
                self.contactProfile = model
            }
        case "411":
            guard let image = UIImage(named: "image") else {
                return
            }
            guard self.contactProfile != nil else {
                return
            }
            let contact1 = CRPContact(contactID: 0, fullName: "0", image: image, phoneNumber: "0")
            let contact2 = CRPContact(contactID: 1, fullName: "1", image: image, phoneNumber: "1")
            let contact3 = CRPContact(contactID: 2, fullName: "2", image: image, phoneNumber: "2")
            let contact4 = CRPContact(contactID: 3, fullName: "3", image: image, phoneNumber: "3")
            let contact5 = CRPContact(contactID: 4, fullName: "4", image: image, phoneNumber: "4")

            let contacts = [contact1, contact2, contact3, contact4, contact5]

            manager.setContact(profile: self.contactProfile, contacts: contacts)

        case "420":
            manager.deleteContact(contactID: 0)
        case "321":
            manager.cleanAllContact()
        case "330":
            manager.getScreenContent { (content, imageSize, compressionType, error) in
                self.imageSize = imageSize
                self.compressionType = compressionType
            }
            break
        case "331":
            guard let image = UIImage(named: "image") else {
                return
            }
            if self.imageSize != nil && self.compressionType != nil {
                manager.startChangeScreen(image, self.imageSize, false, compressionType)
            }
            break
        case "340":
            manager.getStressIsSupport { value, error in
                print("value = \(value)")
            }

        case "341":
            manager.getStressRecord { stressRecordModels, error in
                for model in stressRecordModels{
                    print("value = \(model.value) ,time =\(model.time)")
                }
            }
        case "350":
            manager.setStartStress()
        case "351":
            manager.setStopStress()
        case "360":
            manager.getStockSupportInfo { value, error in
                NSLog("Stock support quantity = \(value)")
            }
        case "361":
            //Clear stock data
            manager.setNullStockData()
        case "370":
            let stock = CRPStockSelectionModel(id: 1, regularMarketOpen: 1000000, regularMarketDayHigh: 100, regularMarketDayLow: 99, regularMarketVolume: 98, peRatio: 2, marketCap: 3, fiftyTwoWeekHigh: 97, fiftyTwoWeekLow: 96, averageDailyVolume3Month: 95, regularMarketPrice: 94, currency: "CNY", shortName: "QWE", symbol: "CN", exchange: "ASD", regularMarketPreviousClose: 03, isOpen: true)
            manager.setStockData(data: stock)
        case "371":
            manager.deleteStock(id: 1)
        case "380":
            manager.setStockSequence(ids: [1,2])

    default:
      result(FlutterMethodNotImplemented)
    }
  }


    func getWatchInfo(model: [Int], currentPage: Int, perPage: Int){
        print("currentPage = \(currentPage), perpage =\(perPage)")
        let manager = CRPSmartBandSDK.sharedInstance
        manager.getWatchFaceInfo(model, currentPage: currentPage, perPage: perPage) { (infos, total, count, err) in
            print("infos = \(infos)")
            if count > 0 {
                self.getWatchInfo(model: model, currentPage: currentPage + 1, perPage: perPage)
            }
        }
    }

}


class DeviceDataReceivedStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("DeviceDataReceivedStreamHandler.onListen called")
        plugin?.deviceDataReceivedSink = events
        print("Device data received sink set: \(plugin?.deviceDataReceivedSink != nil)")
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("DeviceDataReceivedStreamHandler.onCancel called")
        return nil
    }
}

class ConnectionStateStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("ConnectionStateStreamHandler.onListen called")
        plugin?.connectionStateSink = events
        print("Connection state sink set: \(plugin?.connectionStateSink != nil)")
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("ConnectionStateStreamHandler.onCancel called")
        return nil
    }
}

class BatteryLevelStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("BatteryLevelStreamHandler.onListen called")
        plugin?.batteryLevelSink = events
        print("Battery level sink set: \(plugin?.batteryLevelSink != nil)")
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("BatteryLevelStreamHandler.onCancel called")
        return nil
    }
}


class StockChangeStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("StockChangeStreamHandler.onListen called")
        plugin?.stockChangeSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("StockChangeStreamHandler.onCancel called")
        return nil
    }
}

class QuickResponsesStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("QuickResponsesStreamHandler.onListen called")
        plugin?.quickResponsesSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("QuickResponsesStreamHandler.onCancel called")
        return nil
    }
}

class BreathRateStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("BreathRateStreamHandler.onListen called")
        plugin?.breathRateSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("BreathRateStreamHandler.onCancel called")
        return nil
    }
}

class HrvStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("HrvStreamHandler.onListen called")
        plugin?.hrvSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("HrvStreamHandler.onCancel called")
        return nil
    }
}

class TempStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("TempStreamHandler.onListen called")
        plugin?.tempSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("TempStreamHandler.onCancel called")
        return nil
    }
}

class MovementStateStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("MovementStateStreamHandler.onListen called")
        plugin?.movementStateSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("MovementStateStreamHandler.onCancel called")
        return nil
    }
}

class TrainingStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("TrainingStreamHandler.onListen called")
        plugin?.trainingSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("TrainingStreamHandler.onCancel called")
        return nil
    }
}

class SleepActionStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("SleepActionStreamHandler.onListen called")
        plugin?.sleepActionSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("SleepActionStreamHandler.onCancel called")
        return nil
    }
}

class StepsCategoryStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("StepsCategoryStreamHandler.onListen called")
        plugin?.stepsCategorySink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("StepsCategoryStreamHandler.onCancel called")
        return nil
    }
}

class StepChangeStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("StepChangeStreamHandler.onListen called")
        plugin?.stepChangeSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("StepChangeStreamHandler.onCancel called")
        return nil
    }
}

class SleepChangeStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("SleepChangeStreamHandler.onListen called")
        plugin?.sleepChangeSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("SleepChangeStreamHandler.onCancel called")
        return nil
    }
}

class HeartRateStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("HeartRateStreamHandler.onListen called")
        plugin?.heartRateSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("HeartRateStreamHandler.onCancel called")
        return nil
    }
}

class BloodPressureStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("BloodPressureStreamHandler.onListen called")
        plugin?.bloodPressureSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("BloodPressureStreamHandler.onCancel called")
        return nil
    }
}

class BloodOxygenStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("BloodOxygenStreamHandler.onListen called")
        plugin?.bloodOxygenSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("BloodOxygenStreamHandler.onCancel called")
        return nil
    }
}

class ECGStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("ECGStreamHandler.onListen called")
        plugin?.ecgSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("ECGStreamHandler.onCancel called")
        return nil
    }
}

class ContactsStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("ContactsStreamHandler.onListen called")
        plugin?.contactsSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("ContactsStreamHandler.onCancel called")
        return nil
    }
}

class FindPhonesStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("FindPhonesStreamHandler.onListen called")
        plugin?.findPhonesSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("FindPhonesStreamHandler.onCancel called")
        return nil
    }
}

class CameraStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("CameraStreamHandler.onListen called")
        plugin?.cameraSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("CameraStreamHandler.onCancel called")
        return nil
    }
}

class OnLoadingStreamHandler: NSObject, FlutterStreamHandler {
    weak var plugin: DafitSdkPlugin?

    init(plugin: DafitSdkPlugin) {
        self.plugin = plugin
    }

    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        print("OnLoadingStreamHandler.onListen called")
        plugin?.onLoadingSink = events
        return nil
    }

    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        print("OnLoadingStreamHandler.onCancel called")
        return nil
    }
}
