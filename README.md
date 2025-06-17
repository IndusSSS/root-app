flowchart TD
    subgraph Phone["Android Device (Edge Node)"]
        direction TB
        AICore((Gemini Nano))
        CameraX-->EdgeCore
        AudioRecord-->EdgeCore
        Sensors((IMU/GPS/NFC/BLE))-->EdgeCore
        WifiMgr((Local Hotspot))-->EdgeCore
        EdgeCore[[Edge Core Service]]
        RoomDB[(Encrypted Room DB)]
        KtorSrv[[Embedded Ktor Server]]
        EdgeCore-->RoomDB
        EdgeCore-->|local API / WebRTC|KtorSrv
        AICore-->|on-device inference|EdgeCore
    end

    subgraph LAN["Local Clients"]
        Browser-->|HLS/WS|KtorSrv
    end

    subgraph Cloud["Cloud.Smartsecurity.Solutions"]
        FastAPI[[Ingest API]]
        MinIO[(Object Storage)]
        Dashboard((Vue 3 Dashboard))
        FastAPI-->MinIO
        Dashboard<-->|WebSocket|FastAPI
    end

    EdgeCore-->|HTTPS JSON + media|FastAPI
    WifiMgr-->|tethering|LAN
    KtorSrv-->|local stream|Browser

    subgraph DevOps["Dev & CI/CD"]
        ChatGPTCodex[[ChatGPT Codex]]
        GitHubActions[[GitHub Actions CI]]
        GitHubRepo((GitHub Repo))
        ChatGPTCodex-->|scaffold code|GitHubRepo
        GitHubRepo-->|PR / review|GitHubActions
        GitHubActions-->|Signed AAB / APK|Phone
    end
# root-app
