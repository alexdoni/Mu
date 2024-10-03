package com.google.firebase.crashlytics.internal.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.appevents.integrity.IntegrityManager;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.internal.ShareInternalUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class AutoCrashlyticsReportEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoCrashlyticsReportEncoder();

    private AutoCrashlyticsReportEncoder() {
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> encoderConfig) {
        encoderConfig.registerEncoder(CrashlyticsReport.class, CrashlyticsReportEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport.class, CrashlyticsReportEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.class, CrashlyticsReportSessionEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session.class, CrashlyticsReportSessionEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Application.class, CrashlyticsReportSessionApplicationEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Application.class, CrashlyticsReportSessionApplicationEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Application.Organization.class, CrashlyticsReportSessionApplicationOrganizationEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Application_Organization.class, CrashlyticsReportSessionApplicationOrganizationEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.User.class, CrashlyticsReportSessionUserEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_User.class, CrashlyticsReportSessionUserEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.OperatingSystem.class, CrashlyticsReportSessionOperatingSystemEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_OperatingSystem.class, CrashlyticsReportSessionOperatingSystemEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Device.class, CrashlyticsReportSessionDeviceEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Device.class, CrashlyticsReportSessionDeviceEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.class, CrashlyticsReportSessionEventEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Event.class, CrashlyticsReportSessionEventEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.class, CrashlyticsReportSessionEventApplicationEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application.class, CrashlyticsReportSessionEventApplicationEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.class, CrashlyticsReportSessionEventApplicationExecutionEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution.class, CrashlyticsReportSessionEventApplicationExecutionEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Thread.class, CrashlyticsReportSessionEventApplicationExecutionThreadEncoder.INSTANCE);
        encoderConfig.registerEncoder(C1629x7e3e3ebd.class, CrashlyticsReportSessionEventApplicationExecutionThreadEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.class, C1611xc3999712.INSTANCE);
        encoderConfig.registerEncoder(C1630xce3d994b.class, C1611xc3999712.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Exception.class, C1610x55689506.INSTANCE);
        encoderConfig.registerEncoder(C1627xc2f5febc.class, C1610x55689506.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.ApplicationExitInfo.class, CrashlyticsReportApplicationExitInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_ApplicationExitInfo.class, CrashlyticsReportApplicationExitInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.class, CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder.INSTANCE);
        encoderConfig.registerEncoder(C1615xb26d2aa8.class, CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Signal.class, CrashlyticsReportSessionEventApplicationExecutionSignalEncoder.INSTANCE);
        encoderConfig.registerEncoder(C1628x7c929f5b.class, CrashlyticsReportSessionEventApplicationExecutionSignalEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.class, C1609x99c932db.INSTANCE);
        encoderConfig.registerEncoder(C1626xfe724d07.class, C1609x99c932db.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.CustomAttribute.class, CrashlyticsReportCustomAttributeEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_CustomAttribute.class, CrashlyticsReportCustomAttributeEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.ProcessDetails.class, CrashlyticsReportSessionEventApplicationProcessDetailsEncoder.INSTANCE);
        encoderConfig.registerEncoder(C1631x94fa915f.class, CrashlyticsReportSessionEventApplicationProcessDetailsEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Device.class, CrashlyticsReportSessionEventDeviceEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Device.class, CrashlyticsReportSessionEventDeviceEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Log.class, CrashlyticsReportSessionEventLogEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Log.class, CrashlyticsReportSessionEventLogEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.RolloutsState.class, CrashlyticsReportSessionEventRolloutsStateEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_RolloutsState.class, CrashlyticsReportSessionEventRolloutsStateEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.RolloutAssignment.class, CrashlyticsReportSessionEventRolloutAssignmentEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment.class, CrashlyticsReportSessionEventRolloutAssignmentEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.class, C1612x319e1f5b.INSTANCE);
        encoderConfig.registerEncoder(C1635x87204092.class, C1612x319e1f5b.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.FilesPayload.class, CrashlyticsReportFilesPayloadEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_FilesPayload.class, CrashlyticsReportFilesPayloadEncoder.INSTANCE);
        encoderConfig.registerEncoder(CrashlyticsReport.FilesPayload.File.class, CrashlyticsReportFilesPayloadFileEncoder.INSTANCE);
        encoderConfig.registerEncoder(AutoValue_CrashlyticsReport_FilesPayload_File.class, CrashlyticsReportFilesPayloadFileEncoder.INSTANCE);
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportEncoder implements ObjectEncoder<CrashlyticsReport> {
        static final CrashlyticsReportEncoder INSTANCE = new CrashlyticsReportEncoder();
        private static final FieldDescriptor SDKVERSION_DESCRIPTOR = FieldDescriptor.m553of("sdkVersion");
        private static final FieldDescriptor GMPAPPID_DESCRIPTOR = FieldDescriptor.m553of("gmpAppId");
        private static final FieldDescriptor PLATFORM_DESCRIPTOR = FieldDescriptor.m553of("platform");
        private static final FieldDescriptor INSTALLATIONUUID_DESCRIPTOR = FieldDescriptor.m553of("installationUuid");
        private static final FieldDescriptor FIREBASEINSTALLATIONID_DESCRIPTOR = FieldDescriptor.m553of("firebaseInstallationId");
        private static final FieldDescriptor APPQUALITYSESSIONID_DESCRIPTOR = FieldDescriptor.m553of("appQualitySessionId");
        private static final FieldDescriptor BUILDVERSION_DESCRIPTOR = FieldDescriptor.m553of("buildVersion");
        private static final FieldDescriptor DISPLAYVERSION_DESCRIPTOR = FieldDescriptor.m553of("displayVersion");
        private static final FieldDescriptor SESSION_DESCRIPTOR = FieldDescriptor.m553of("session");
        private static final FieldDescriptor NDKPAYLOAD_DESCRIPTOR = FieldDescriptor.m553of("ndkPayload");
        private static final FieldDescriptor APPEXITINFO_DESCRIPTOR = FieldDescriptor.m553of("appExitInfo");

        private CrashlyticsReportEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport crashlyticsReport, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(SDKVERSION_DESCRIPTOR, crashlyticsReport.getSdkVersion());
            objectEncoderContext.add(GMPAPPID_DESCRIPTOR, crashlyticsReport.getGmpAppId());
            objectEncoderContext.add(PLATFORM_DESCRIPTOR, crashlyticsReport.getPlatform());
            objectEncoderContext.add(INSTALLATIONUUID_DESCRIPTOR, crashlyticsReport.getInstallationUuid());
            objectEncoderContext.add(FIREBASEINSTALLATIONID_DESCRIPTOR, crashlyticsReport.getFirebaseInstallationId());
            objectEncoderContext.add(APPQUALITYSESSIONID_DESCRIPTOR, crashlyticsReport.getAppQualitySessionId());
            objectEncoderContext.add(BUILDVERSION_DESCRIPTOR, crashlyticsReport.getBuildVersion());
            objectEncoderContext.add(DISPLAYVERSION_DESCRIPTOR, crashlyticsReport.getDisplayVersion());
            objectEncoderContext.add(SESSION_DESCRIPTOR, crashlyticsReport.getSession());
            objectEncoderContext.add(NDKPAYLOAD_DESCRIPTOR, crashlyticsReport.getNdkPayload());
            objectEncoderContext.add(APPEXITINFO_DESCRIPTOR, crashlyticsReport.getAppExitInfo());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionEncoder implements ObjectEncoder<CrashlyticsReport.Session> {
        static final CrashlyticsReportSessionEncoder INSTANCE = new CrashlyticsReportSessionEncoder();
        private static final FieldDescriptor GENERATOR_DESCRIPTOR = FieldDescriptor.m553of("generator");
        private static final FieldDescriptor IDENTIFIER_DESCRIPTOR = FieldDescriptor.m553of("identifier");
        private static final FieldDescriptor APPQUALITYSESSIONID_DESCRIPTOR = FieldDescriptor.m553of("appQualitySessionId");
        private static final FieldDescriptor STARTEDAT_DESCRIPTOR = FieldDescriptor.m553of("startedAt");
        private static final FieldDescriptor ENDEDAT_DESCRIPTOR = FieldDescriptor.m553of("endedAt");
        private static final FieldDescriptor CRASHED_DESCRIPTOR = FieldDescriptor.m553of("crashed");
        private static final FieldDescriptor APP_DESCRIPTOR = FieldDescriptor.m553of("app");
        private static final FieldDescriptor USER_DESCRIPTOR = FieldDescriptor.m553of("user");
        private static final FieldDescriptor OS_DESCRIPTOR = FieldDescriptor.m553of("os");
        private static final FieldDescriptor DEVICE_DESCRIPTOR = FieldDescriptor.m553of(DeviceRequestsHelper.DEVICE_INFO_DEVICE);
        private static final FieldDescriptor EVENTS_DESCRIPTOR = FieldDescriptor.m553of("events");
        private static final FieldDescriptor GENERATORTYPE_DESCRIPTOR = FieldDescriptor.m553of("generatorType");

        private CrashlyticsReportSessionEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session session, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(GENERATOR_DESCRIPTOR, session.getGenerator());
            objectEncoderContext.add(IDENTIFIER_DESCRIPTOR, session.getIdentifierUtf8Bytes());
            objectEncoderContext.add(APPQUALITYSESSIONID_DESCRIPTOR, session.getAppQualitySessionId());
            objectEncoderContext.add(STARTEDAT_DESCRIPTOR, session.getStartedAt());
            objectEncoderContext.add(ENDEDAT_DESCRIPTOR, session.getEndedAt());
            objectEncoderContext.add(CRASHED_DESCRIPTOR, session.isCrashed());
            objectEncoderContext.add(APP_DESCRIPTOR, session.getApp());
            objectEncoderContext.add(USER_DESCRIPTOR, session.getUser());
            objectEncoderContext.add(OS_DESCRIPTOR, session.getOs());
            objectEncoderContext.add(DEVICE_DESCRIPTOR, session.getDevice());
            objectEncoderContext.add(EVENTS_DESCRIPTOR, session.getEvents());
            objectEncoderContext.add(GENERATORTYPE_DESCRIPTOR, session.getGeneratorType());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionApplicationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Application> {
        static final CrashlyticsReportSessionApplicationEncoder INSTANCE = new CrashlyticsReportSessionApplicationEncoder();
        private static final FieldDescriptor IDENTIFIER_DESCRIPTOR = FieldDescriptor.m553of("identifier");
        private static final FieldDescriptor VERSION_DESCRIPTOR = FieldDescriptor.m553of(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
        private static final FieldDescriptor DISPLAYVERSION_DESCRIPTOR = FieldDescriptor.m553of("displayVersion");
        private static final FieldDescriptor ORGANIZATION_DESCRIPTOR = FieldDescriptor.m553of("organization");
        private static final FieldDescriptor INSTALLATIONUUID_DESCRIPTOR = FieldDescriptor.m553of("installationUuid");
        private static final FieldDescriptor DEVELOPMENTPLATFORM_DESCRIPTOR = FieldDescriptor.m553of("developmentPlatform");
        private static final FieldDescriptor DEVELOPMENTPLATFORMVERSION_DESCRIPTOR = FieldDescriptor.m553of("developmentPlatformVersion");

        private CrashlyticsReportSessionApplicationEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Application application, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(IDENTIFIER_DESCRIPTOR, application.getIdentifier());
            objectEncoderContext.add(VERSION_DESCRIPTOR, application.getVersion());
            objectEncoderContext.add(DISPLAYVERSION_DESCRIPTOR, application.getDisplayVersion());
            objectEncoderContext.add(ORGANIZATION_DESCRIPTOR, application.getOrganization());
            objectEncoderContext.add(INSTALLATIONUUID_DESCRIPTOR, application.getInstallationUuid());
            objectEncoderContext.add(DEVELOPMENTPLATFORM_DESCRIPTOR, application.getDevelopmentPlatform());
            objectEncoderContext.add(DEVELOPMENTPLATFORMVERSION_DESCRIPTOR, application.getDevelopmentPlatformVersion());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionApplicationOrganizationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Application.Organization> {
        static final CrashlyticsReportSessionApplicationOrganizationEncoder INSTANCE = new CrashlyticsReportSessionApplicationOrganizationEncoder();
        private static final FieldDescriptor CLSID_DESCRIPTOR = FieldDescriptor.m553of("clsId");

        private CrashlyticsReportSessionApplicationOrganizationEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Application.Organization organization, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(CLSID_DESCRIPTOR, organization.getClsId());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionUserEncoder implements ObjectEncoder<CrashlyticsReport.Session.User> {
        static final CrashlyticsReportSessionUserEncoder INSTANCE = new CrashlyticsReportSessionUserEncoder();
        private static final FieldDescriptor IDENTIFIER_DESCRIPTOR = FieldDescriptor.m553of("identifier");

        private CrashlyticsReportSessionUserEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.User user, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(IDENTIFIER_DESCRIPTOR, user.getIdentifier());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionOperatingSystemEncoder implements ObjectEncoder<CrashlyticsReport.Session.OperatingSystem> {
        static final CrashlyticsReportSessionOperatingSystemEncoder INSTANCE = new CrashlyticsReportSessionOperatingSystemEncoder();
        private static final FieldDescriptor PLATFORM_DESCRIPTOR = FieldDescriptor.m553of("platform");
        private static final FieldDescriptor VERSION_DESCRIPTOR = FieldDescriptor.m553of(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
        private static final FieldDescriptor BUILDVERSION_DESCRIPTOR = FieldDescriptor.m553of("buildVersion");
        private static final FieldDescriptor JAILBROKEN_DESCRIPTOR = FieldDescriptor.m553of("jailbroken");

        private CrashlyticsReportSessionOperatingSystemEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.OperatingSystem operatingSystem, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PLATFORM_DESCRIPTOR, operatingSystem.getPlatform());
            objectEncoderContext.add(VERSION_DESCRIPTOR, operatingSystem.getVersion());
            objectEncoderContext.add(BUILDVERSION_DESCRIPTOR, operatingSystem.getBuildVersion());
            objectEncoderContext.add(JAILBROKEN_DESCRIPTOR, operatingSystem.isJailbroken());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionDeviceEncoder implements ObjectEncoder<CrashlyticsReport.Session.Device> {
        static final CrashlyticsReportSessionDeviceEncoder INSTANCE = new CrashlyticsReportSessionDeviceEncoder();
        private static final FieldDescriptor ARCH_DESCRIPTOR = FieldDescriptor.m553of("arch");
        private static final FieldDescriptor MODEL_DESCRIPTOR = FieldDescriptor.m553of("model");
        private static final FieldDescriptor CORES_DESCRIPTOR = FieldDescriptor.m553of("cores");
        private static final FieldDescriptor RAM_DESCRIPTOR = FieldDescriptor.m553of("ram");
        private static final FieldDescriptor DISKSPACE_DESCRIPTOR = FieldDescriptor.m553of("diskSpace");
        private static final FieldDescriptor SIMULATOR_DESCRIPTOR = FieldDescriptor.m553of("simulator");
        private static final FieldDescriptor STATE_DESCRIPTOR = FieldDescriptor.m553of(ServerProtocol.DIALOG_PARAM_STATE);
        private static final FieldDescriptor MANUFACTURER_DESCRIPTOR = FieldDescriptor.m553of("manufacturer");
        private static final FieldDescriptor MODELCLASS_DESCRIPTOR = FieldDescriptor.m553of("modelClass");

        private CrashlyticsReportSessionDeviceEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Device device, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(ARCH_DESCRIPTOR, device.getArch());
            objectEncoderContext.add(MODEL_DESCRIPTOR, device.getModel());
            objectEncoderContext.add(CORES_DESCRIPTOR, device.getCores());
            objectEncoderContext.add(RAM_DESCRIPTOR, device.getRam());
            objectEncoderContext.add(DISKSPACE_DESCRIPTOR, device.getDiskSpace());
            objectEncoderContext.add(SIMULATOR_DESCRIPTOR, device.isSimulator());
            objectEncoderContext.add(STATE_DESCRIPTOR, device.getState());
            objectEncoderContext.add(MANUFACTURER_DESCRIPTOR, device.getManufacturer());
            objectEncoderContext.add(MODELCLASS_DESCRIPTOR, device.getModelClass());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionEventEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event> {
        static final CrashlyticsReportSessionEventEncoder INSTANCE = new CrashlyticsReportSessionEventEncoder();
        private static final FieldDescriptor TIMESTAMP_DESCRIPTOR = FieldDescriptor.m553of("timestamp");
        private static final FieldDescriptor TYPE_DESCRIPTOR = FieldDescriptor.m553of(ShareConstants.MEDIA_TYPE);
        private static final FieldDescriptor APP_DESCRIPTOR = FieldDescriptor.m553of("app");
        private static final FieldDescriptor DEVICE_DESCRIPTOR = FieldDescriptor.m553of(DeviceRequestsHelper.DEVICE_INFO_DEVICE);
        private static final FieldDescriptor LOG_DESCRIPTOR = FieldDescriptor.m553of("log");
        private static final FieldDescriptor ROLLOUTS_DESCRIPTOR = FieldDescriptor.m553of("rollouts");

        private CrashlyticsReportSessionEventEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event event, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(TIMESTAMP_DESCRIPTOR, event.getTimestamp());
            objectEncoderContext.add(TYPE_DESCRIPTOR, event.getType());
            objectEncoderContext.add(APP_DESCRIPTOR, event.getApp());
            objectEncoderContext.add(DEVICE_DESCRIPTOR, event.getDevice());
            objectEncoderContext.add(LOG_DESCRIPTOR, event.getLog());
            objectEncoderContext.add(ROLLOUTS_DESCRIPTOR, event.getRollouts());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionEventApplicationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application> {
        static final CrashlyticsReportSessionEventApplicationEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationEncoder();
        private static final FieldDescriptor EXECUTION_DESCRIPTOR = FieldDescriptor.m553of("execution");
        private static final FieldDescriptor CUSTOMATTRIBUTES_DESCRIPTOR = FieldDescriptor.m553of("customAttributes");
        private static final FieldDescriptor INTERNALKEYS_DESCRIPTOR = FieldDescriptor.m553of("internalKeys");
        private static final FieldDescriptor BACKGROUND_DESCRIPTOR = FieldDescriptor.m553of("background");
        private static final FieldDescriptor CURRENTPROCESSDETAILS_DESCRIPTOR = FieldDescriptor.m553of("currentProcessDetails");
        private static final FieldDescriptor APPPROCESSDETAILS_DESCRIPTOR = FieldDescriptor.m553of("appProcessDetails");
        private static final FieldDescriptor UIORIENTATION_DESCRIPTOR = FieldDescriptor.m553of("uiOrientation");

        private CrashlyticsReportSessionEventApplicationEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application application, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(EXECUTION_DESCRIPTOR, application.getExecution());
            objectEncoderContext.add(CUSTOMATTRIBUTES_DESCRIPTOR, application.getCustomAttributes());
            objectEncoderContext.add(INTERNALKEYS_DESCRIPTOR, application.getInternalKeys());
            objectEncoderContext.add(BACKGROUND_DESCRIPTOR, application.getBackground());
            objectEncoderContext.add(CURRENTPROCESSDETAILS_DESCRIPTOR, application.getCurrentProcessDetails());
            objectEncoderContext.add(APPPROCESSDETAILS_DESCRIPTOR, application.getAppProcessDetails());
            objectEncoderContext.add(UIORIENTATION_DESCRIPTOR, application.getUiOrientation());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionEventApplicationExecutionEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution> {
        static final CrashlyticsReportSessionEventApplicationExecutionEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionEncoder();
        private static final FieldDescriptor THREADS_DESCRIPTOR = FieldDescriptor.m553of("threads");
        private static final FieldDescriptor EXCEPTION_DESCRIPTOR = FieldDescriptor.m553of("exception");
        private static final FieldDescriptor APPEXITINFO_DESCRIPTOR = FieldDescriptor.m553of("appExitInfo");
        private static final FieldDescriptor SIGNAL_DESCRIPTOR = FieldDescriptor.m553of("signal");
        private static final FieldDescriptor BINARIES_DESCRIPTOR = FieldDescriptor.m553of("binaries");

        private CrashlyticsReportSessionEventApplicationExecutionEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.Execution execution, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(THREADS_DESCRIPTOR, execution.getThreads());
            objectEncoderContext.add(EXCEPTION_DESCRIPTOR, execution.getException());
            objectEncoderContext.add(APPEXITINFO_DESCRIPTOR, execution.getAppExitInfo());
            objectEncoderContext.add(SIGNAL_DESCRIPTOR, execution.getSignal());
            objectEncoderContext.add(BINARIES_DESCRIPTOR, execution.getBinaries());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionEventApplicationExecutionThreadEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread> {
        static final CrashlyticsReportSessionEventApplicationExecutionThreadEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadEncoder();
        private static final FieldDescriptor NAME_DESCRIPTOR = FieldDescriptor.m553of("name");
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = FieldDescriptor.m553of("importance");
        private static final FieldDescriptor FRAMES_DESCRIPTOR = FieldDescriptor.m553of("frames");

        private CrashlyticsReportSessionEventApplicationExecutionThreadEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread thread, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(NAME_DESCRIPTOR, thread.getName());
            objectEncoderContext.add(IMPORTANCE_DESCRIPTOR, thread.getImportance());
            objectEncoderContext.add(FRAMES_DESCRIPTOR, thread.getFrames());
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoCrashlyticsReportEncoder$CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder */
    /* loaded from: classes2.dex */
    private static final class C1611xc3999712 implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> {
        static final C1611xc3999712 INSTANCE = new C1611xc3999712();
        private static final FieldDescriptor PC_DESCRIPTOR = FieldDescriptor.m553of("pc");
        private static final FieldDescriptor SYMBOL_DESCRIPTOR = FieldDescriptor.m553of("symbol");
        private static final FieldDescriptor FILE_DESCRIPTOR = FieldDescriptor.m553of(ShareInternalUtility.STAGING_PARAM);
        private static final FieldDescriptor OFFSET_DESCRIPTOR = FieldDescriptor.m553of(TypedValues.CycleType.S_WAVE_OFFSET);
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = FieldDescriptor.m553of("importance");

        private C1611xc3999712() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame frame, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PC_DESCRIPTOR, frame.getPc());
            objectEncoderContext.add(SYMBOL_DESCRIPTOR, frame.getSymbol());
            objectEncoderContext.add(FILE_DESCRIPTOR, frame.getFile());
            objectEncoderContext.add(OFFSET_DESCRIPTOR, frame.getOffset());
            objectEncoderContext.add(IMPORTANCE_DESCRIPTOR, frame.getImportance());
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoCrashlyticsReportEncoder$CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder */
    /* loaded from: classes2.dex */
    private static final class C1610x55689506 implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Exception> {
        static final C1610x55689506 INSTANCE = new C1610x55689506();
        private static final FieldDescriptor TYPE_DESCRIPTOR = FieldDescriptor.m553of(ShareConstants.MEDIA_TYPE);
        private static final FieldDescriptor REASON_DESCRIPTOR = FieldDescriptor.m553of("reason");
        private static final FieldDescriptor FRAMES_DESCRIPTOR = FieldDescriptor.m553of("frames");
        private static final FieldDescriptor CAUSEDBY_DESCRIPTOR = FieldDescriptor.m553of("causedBy");
        private static final FieldDescriptor OVERFLOWCOUNT_DESCRIPTOR = FieldDescriptor.m553of("overflowCount");

        private C1610x55689506() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Exception exception, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(TYPE_DESCRIPTOR, exception.getType());
            objectEncoderContext.add(REASON_DESCRIPTOR, exception.getReason());
            objectEncoderContext.add(FRAMES_DESCRIPTOR, exception.getFrames());
            objectEncoderContext.add(CAUSEDBY_DESCRIPTOR, exception.getCausedBy());
            objectEncoderContext.add(OVERFLOWCOUNT_DESCRIPTOR, exception.getOverflowCount());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportApplicationExitInfoEncoder implements ObjectEncoder<CrashlyticsReport.ApplicationExitInfo> {
        static final CrashlyticsReportApplicationExitInfoEncoder INSTANCE = new CrashlyticsReportApplicationExitInfoEncoder();
        private static final FieldDescriptor PID_DESCRIPTOR = FieldDescriptor.m553of("pid");
        private static final FieldDescriptor PROCESSNAME_DESCRIPTOR = FieldDescriptor.m553of("processName");
        private static final FieldDescriptor REASONCODE_DESCRIPTOR = FieldDescriptor.m553of("reasonCode");
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = FieldDescriptor.m553of("importance");
        private static final FieldDescriptor PSS_DESCRIPTOR = FieldDescriptor.m553of("pss");
        private static final FieldDescriptor RSS_DESCRIPTOR = FieldDescriptor.m553of("rss");
        private static final FieldDescriptor TIMESTAMP_DESCRIPTOR = FieldDescriptor.m553of("timestamp");
        private static final FieldDescriptor TRACEFILE_DESCRIPTOR = FieldDescriptor.m553of("traceFile");
        private static final FieldDescriptor BUILDIDMAPPINGFORARCH_DESCRIPTOR = FieldDescriptor.m553of("buildIdMappingForArch");

        private CrashlyticsReportApplicationExitInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.ApplicationExitInfo applicationExitInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PID_DESCRIPTOR, applicationExitInfo.getPid());
            objectEncoderContext.add(PROCESSNAME_DESCRIPTOR, applicationExitInfo.getProcessName());
            objectEncoderContext.add(REASONCODE_DESCRIPTOR, applicationExitInfo.getReasonCode());
            objectEncoderContext.add(IMPORTANCE_DESCRIPTOR, applicationExitInfo.getImportance());
            objectEncoderContext.add(PSS_DESCRIPTOR, applicationExitInfo.getPss());
            objectEncoderContext.add(RSS_DESCRIPTOR, applicationExitInfo.getRss());
            objectEncoderContext.add(TIMESTAMP_DESCRIPTOR, applicationExitInfo.getTimestamp());
            objectEncoderContext.add(TRACEFILE_DESCRIPTOR, applicationExitInfo.getTraceFile());
            objectEncoderContext.add(BUILDIDMAPPINGFORARCH_DESCRIPTOR, applicationExitInfo.getBuildIdMappingForArch());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder implements ObjectEncoder<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> {
        static final CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder INSTANCE = new CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder();
        private static final FieldDescriptor ARCH_DESCRIPTOR = FieldDescriptor.m553of("arch");
        private static final FieldDescriptor LIBRARYNAME_DESCRIPTOR = FieldDescriptor.m553of("libraryName");
        private static final FieldDescriptor BUILDID_DESCRIPTOR = FieldDescriptor.m553of("buildId");

        private CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch buildIdMappingForArch, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(ARCH_DESCRIPTOR, buildIdMappingForArch.getArch());
            objectEncoderContext.add(LIBRARYNAME_DESCRIPTOR, buildIdMappingForArch.getLibraryName());
            objectEncoderContext.add(BUILDID_DESCRIPTOR, buildIdMappingForArch.getBuildId());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionEventApplicationExecutionSignalEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Signal> {
        static final CrashlyticsReportSessionEventApplicationExecutionSignalEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionSignalEncoder();
        private static final FieldDescriptor NAME_DESCRIPTOR = FieldDescriptor.m553of("name");
        private static final FieldDescriptor CODE_DESCRIPTOR = FieldDescriptor.m553of("code");
        private static final FieldDescriptor ADDRESS_DESCRIPTOR = FieldDescriptor.m553of(IntegrityManager.INTEGRITY_TYPE_ADDRESS);

        private CrashlyticsReportSessionEventApplicationExecutionSignalEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Signal signal, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(NAME_DESCRIPTOR, signal.getName());
            objectEncoderContext.add(CODE_DESCRIPTOR, signal.getCode());
            objectEncoderContext.add(ADDRESS_DESCRIPTOR, signal.getAddress());
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoCrashlyticsReportEncoder$CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder */
    /* loaded from: classes2.dex */
    private static final class C1609x99c932db implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> {
        static final C1609x99c932db INSTANCE = new C1609x99c932db();
        private static final FieldDescriptor BASEADDRESS_DESCRIPTOR = FieldDescriptor.m553of("baseAddress");
        private static final FieldDescriptor SIZE_DESCRIPTOR = FieldDescriptor.m553of("size");
        private static final FieldDescriptor NAME_DESCRIPTOR = FieldDescriptor.m553of("name");
        private static final FieldDescriptor UUID_DESCRIPTOR = FieldDescriptor.m553of("uuid");

        private C1609x99c932db() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage binaryImage, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(BASEADDRESS_DESCRIPTOR, binaryImage.getBaseAddress());
            objectEncoderContext.add(SIZE_DESCRIPTOR, binaryImage.getSize());
            objectEncoderContext.add(NAME_DESCRIPTOR, binaryImage.getName());
            objectEncoderContext.add(UUID_DESCRIPTOR, binaryImage.getUuidUtf8Bytes());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportCustomAttributeEncoder implements ObjectEncoder<CrashlyticsReport.CustomAttribute> {
        static final CrashlyticsReportCustomAttributeEncoder INSTANCE = new CrashlyticsReportCustomAttributeEncoder();
        private static final FieldDescriptor KEY_DESCRIPTOR = FieldDescriptor.m553of("key");
        private static final FieldDescriptor VALUE_DESCRIPTOR = FieldDescriptor.m553of("value");

        private CrashlyticsReportCustomAttributeEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.CustomAttribute customAttribute, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(KEY_DESCRIPTOR, customAttribute.getKey());
            objectEncoderContext.add(VALUE_DESCRIPTOR, customAttribute.getValue());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionEventApplicationProcessDetailsEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.ProcessDetails> {
        static final CrashlyticsReportSessionEventApplicationProcessDetailsEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationProcessDetailsEncoder();
        private static final FieldDescriptor PROCESSNAME_DESCRIPTOR = FieldDescriptor.m553of("processName");
        private static final FieldDescriptor PID_DESCRIPTOR = FieldDescriptor.m553of("pid");
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = FieldDescriptor.m553of("importance");
        private static final FieldDescriptor DEFAULTPROCESS_DESCRIPTOR = FieldDescriptor.m553of("defaultProcess");

        private CrashlyticsReportSessionEventApplicationProcessDetailsEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Application.ProcessDetails processDetails, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PROCESSNAME_DESCRIPTOR, processDetails.getProcessName());
            objectEncoderContext.add(PID_DESCRIPTOR, processDetails.getPid());
            objectEncoderContext.add(IMPORTANCE_DESCRIPTOR, processDetails.getImportance());
            objectEncoderContext.add(DEFAULTPROCESS_DESCRIPTOR, processDetails.isDefaultProcess());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionEventDeviceEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Device> {
        static final CrashlyticsReportSessionEventDeviceEncoder INSTANCE = new CrashlyticsReportSessionEventDeviceEncoder();
        private static final FieldDescriptor BATTERYLEVEL_DESCRIPTOR = FieldDescriptor.m553of("batteryLevel");
        private static final FieldDescriptor BATTERYVELOCITY_DESCRIPTOR = FieldDescriptor.m553of("batteryVelocity");
        private static final FieldDescriptor PROXIMITYON_DESCRIPTOR = FieldDescriptor.m553of("proximityOn");
        private static final FieldDescriptor ORIENTATION_DESCRIPTOR = FieldDescriptor.m553of("orientation");
        private static final FieldDescriptor RAMUSED_DESCRIPTOR = FieldDescriptor.m553of("ramUsed");
        private static final FieldDescriptor DISKUSED_DESCRIPTOR = FieldDescriptor.m553of("diskUsed");

        private CrashlyticsReportSessionEventDeviceEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Device device, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(BATTERYLEVEL_DESCRIPTOR, device.getBatteryLevel());
            objectEncoderContext.add(BATTERYVELOCITY_DESCRIPTOR, device.getBatteryVelocity());
            objectEncoderContext.add(PROXIMITYON_DESCRIPTOR, device.isProximityOn());
            objectEncoderContext.add(ORIENTATION_DESCRIPTOR, device.getOrientation());
            objectEncoderContext.add(RAMUSED_DESCRIPTOR, device.getRamUsed());
            objectEncoderContext.add(DISKUSED_DESCRIPTOR, device.getDiskUsed());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionEventLogEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Log> {
        static final CrashlyticsReportSessionEventLogEncoder INSTANCE = new CrashlyticsReportSessionEventLogEncoder();
        private static final FieldDescriptor CONTENT_DESCRIPTOR = FieldDescriptor.m553of(FirebaseAnalytics.Param.CONTENT);

        private CrashlyticsReportSessionEventLogEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.Log log, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(CONTENT_DESCRIPTOR, log.getContent());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionEventRolloutsStateEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.RolloutsState> {
        static final CrashlyticsReportSessionEventRolloutsStateEncoder INSTANCE = new CrashlyticsReportSessionEventRolloutsStateEncoder();
        private static final FieldDescriptor ASSIGNMENTS_DESCRIPTOR = FieldDescriptor.m553of("assignments");

        private CrashlyticsReportSessionEventRolloutsStateEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.RolloutsState rolloutsState, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(ASSIGNMENTS_DESCRIPTOR, rolloutsState.getRolloutAssignments());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportSessionEventRolloutAssignmentEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.RolloutAssignment> {
        static final CrashlyticsReportSessionEventRolloutAssignmentEncoder INSTANCE = new CrashlyticsReportSessionEventRolloutAssignmentEncoder();
        private static final FieldDescriptor ROLLOUTVARIANT_DESCRIPTOR = FieldDescriptor.m553of("rolloutVariant");
        private static final FieldDescriptor PARAMETERKEY_DESCRIPTOR = FieldDescriptor.m553of("parameterKey");
        private static final FieldDescriptor PARAMETERVALUE_DESCRIPTOR = FieldDescriptor.m553of("parameterValue");
        private static final FieldDescriptor TEMPLATEVERSION_DESCRIPTOR = FieldDescriptor.m553of("templateVersion");

        private CrashlyticsReportSessionEventRolloutAssignmentEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.RolloutAssignment rolloutAssignment, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(ROLLOUTVARIANT_DESCRIPTOR, rolloutAssignment.getRolloutVariant());
            objectEncoderContext.add(PARAMETERKEY_DESCRIPTOR, rolloutAssignment.getParameterKey());
            objectEncoderContext.add(PARAMETERVALUE_DESCRIPTOR, rolloutAssignment.getParameterValue());
            objectEncoderContext.add(TEMPLATEVERSION_DESCRIPTOR, rolloutAssignment.getTemplateVersion());
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoCrashlyticsReportEncoder$CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder */
    /* loaded from: classes2.dex */
    private static final class C1612x319e1f5b implements ObjectEncoder<CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant> {
        static final C1612x319e1f5b INSTANCE = new C1612x319e1f5b();
        private static final FieldDescriptor ROLLOUTID_DESCRIPTOR = FieldDescriptor.m553of("rolloutId");
        private static final FieldDescriptor VARIANTID_DESCRIPTOR = FieldDescriptor.m553of("variantId");

        private C1612x319e1f5b() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant rolloutVariant, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(ROLLOUTID_DESCRIPTOR, rolloutVariant.getRolloutId());
            objectEncoderContext.add(VARIANTID_DESCRIPTOR, rolloutVariant.getVariantId());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportFilesPayloadEncoder implements ObjectEncoder<CrashlyticsReport.FilesPayload> {
        static final CrashlyticsReportFilesPayloadEncoder INSTANCE = new CrashlyticsReportFilesPayloadEncoder();
        private static final FieldDescriptor FILES_DESCRIPTOR = FieldDescriptor.m553of("files");
        private static final FieldDescriptor ORGID_DESCRIPTOR = FieldDescriptor.m553of("orgId");

        private CrashlyticsReportFilesPayloadEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.FilesPayload filesPayload, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(FILES_DESCRIPTOR, filesPayload.getFiles());
            objectEncoderContext.add(ORGID_DESCRIPTOR, filesPayload.getOrgId());
        }
    }

    /* loaded from: classes2.dex */
    private static final class CrashlyticsReportFilesPayloadFileEncoder implements ObjectEncoder<CrashlyticsReport.FilesPayload.File> {
        static final CrashlyticsReportFilesPayloadFileEncoder INSTANCE = new CrashlyticsReportFilesPayloadFileEncoder();
        private static final FieldDescriptor FILENAME_DESCRIPTOR = FieldDescriptor.m553of(FileDownloadModel.FILENAME);
        private static final FieldDescriptor CONTENTS_DESCRIPTOR = FieldDescriptor.m553of("contents");

        private CrashlyticsReportFilesPayloadFileEncoder() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(CrashlyticsReport.FilesPayload.File file, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(FILENAME_DESCRIPTOR, file.getFilename());
            objectEncoderContext.add(CONTENTS_DESCRIPTOR, file.getContents());
        }
    }
}
