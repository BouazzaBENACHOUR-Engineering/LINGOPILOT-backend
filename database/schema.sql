USE [AiLanguageApp]
GO
/****** Objet : Table [dbo].[ai_assessments] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ai_assessments](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[conversation_id] [bigint] NOT NULL,
	[skill_id] [bigint] NOT NULL,
	[level_id] [bigint] NULL,
	[score] [decimal](5, 2) NOT NULL,
	[feedback] [nvarchar](max) NULL,
	[created_at] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Objet : Table [dbo].[ai_conversations] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ai_conversations](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[language_id] [bigint] NOT NULL,
	[title] [nvarchar](255) NULL,
	[level_id] [bigint] NULL,
	[started_at] [datetime2](7) NOT NULL,
	[ended_at] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[ai_messages] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ai_messages](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[conversation_id] [bigint] NOT NULL,
	[role] [varchar](20) NOT NULL,
	[content] [nvarchar](max) NOT NULL,
	[created_at] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Objet : Table [dbo].[conversation_feedback] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[conversation_feedback](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[message_id] [bigint] NOT NULL,
	[grammar_score] [decimal](5, 2) NULL,
	[vocabulary_score] [decimal](5, 2) NULL,
	[fluency_score] [decimal](5, 2) NULL,
	[pronunciation_score] [decimal](5, 2) NULL,
	[feedback] [nvarchar](max) NULL,
	[created_at] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_feedback_message] UNIQUE NONCLUSTERED 
(
	[message_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Objet : Table [dbo].[courses] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[courses](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[language_id] [bigint] NOT NULL,
	[level_id] [bigint] NOT NULL,
	[title] [nvarchar](255) NOT NULL,
	[description] [nvarchar](max) NULL,
	[is_active] [bit] NOT NULL,
	[created_at] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Objet : Table [dbo].[exercise_options] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[exercise_options](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[exercise_id] [bigint] NOT NULL,
	[option_text] [nvarchar](1000) NOT NULL,
	[is_correct] [bit] NOT NULL,
	[sequence] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_exercise_options_sequence] UNIQUE NONCLUSTERED 
(
	[exercise_id] ASC,
	[sequence] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[exercises] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[exercises](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[lesson_id] [bigint] NOT NULL,
	[type] [varchar](30) NOT NULL,
	[question] [nvarchar](max) NOT NULL,
	[difficulty] [int] NOT NULL,
	[points] [int] NOT NULL,
	[sequence] [int] NOT NULL,
	[created_at] [datetime2](7) NOT NULL,
	[correct_answer] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_exercises_sequence] UNIQUE NONCLUSTERED 
(
	[lesson_id] ASC,
	[sequence] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Objet : Table [dbo].[languages] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[languages](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[code] [varchar](10) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[is_active] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_languages_code] UNIQUE NONCLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[lesson_sections] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lesson_sections](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[lesson_id] [bigint] NOT NULL,
	[section_type] [varchar](30) NOT NULL,
	[title] [nvarchar](255) NULL,
	[content] [nvarchar](max) NOT NULL,
	[sequence] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_lesson_sections_sequence] UNIQUE NONCLUSTERED 
(
	[lesson_id] ASC,
	[sequence] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Objet : Table [dbo].[lessons] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lessons](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[module_id] [bigint] NOT NULL,
	[skill_id] [bigint] NOT NULL,
	[title] [nvarchar](255) NOT NULL,
	[description] [nvarchar](max) NULL,
	[sequence] [int] NOT NULL,
	[estimated_minutes] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_lessons_sequence] UNIQUE NONCLUSTERED 
(
	[module_id] ASC,
	[sequence] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Objet : Table [dbo].[levels] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[levels](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[code] [varchar](10) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_levels_code] UNIQUE NONCLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[modules] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[modules](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[course_id] [bigint] NOT NULL,
	[title] [nvarchar](255) NOT NULL,
	[description] [nvarchar](max) NULL,
	[sequence] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_modules_sequence] UNIQUE NONCLUSTERED 
(
	[course_id] ASC,
	[sequence] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Objet : Table [dbo].[notifications] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[notifications](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[title] [nvarchar](255) NOT NULL,
	[message] [nvarchar](max) NOT NULL,
	[type] [varchar](50) NOT NULL,
	[is_read] [bit] NOT NULL,
	[created_at] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Objet : Table [dbo].[payments] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[payments](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[subscription_id] [bigint] NULL,
	[amount] [decimal](10, 2) NOT NULL,
	[currency] [char](3) NOT NULL,
	[status] [varchar](20) NOT NULL,
	[payment_provider] [varchar](50) NULL,
	[transaction_reference] [nvarchar](255) NULL,
	[created_at] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[roles] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roles](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_roles_name] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[skills] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[skills](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_skills_name] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[subscription_plans] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[subscription_plans](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[price] [decimal](10, 2) NOT NULL,
	[currency] [char](3) NOT NULL,
	[billing_period] [varchar](20) NOT NULL,
	[ai_messages_limit] [int] NULL,
	[speaking_limit] [int] NULL,
	[is_active] [bit] NOT NULL,
	[conversation_limit] [int] NULL,
	[ads_enabled] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_subscription_plans_name] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[subscription_usage] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[subscription_usage](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[subscription_id] [bigint] NOT NULL,
	[ai_messages_used] [int] NOT NULL,
	[speaking_used] [int] NOT NULL,
	[period_started_at] [datetime2](7) NOT NULL,
	[period_ends_at] [datetime2](7) NULL,
	[updated_at] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[subscriptions] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[subscriptions](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[plan_id] [bigint] NOT NULL,
	[status] [varchar](20) NOT NULL,
	[started_at] [datetime2](7) NOT NULL,
	[expires_at] [datetime2](7) NULL,
	[auto_renew] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[user_exercise_attempts] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_exercise_attempts](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[exercise_id] [bigint] NOT NULL,
	[answer] [nvarchar](max) NULL,
	[is_correct] [bit] NULL,
	[score] [decimal](5, 2) NULL,
	[attempted_at] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Objet : Table [dbo].[user_languages] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_languages](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[language_id] [bigint] NOT NULL,
	[level_id] [bigint] NOT NULL,
	[is_primary] [bit] NOT NULL,
	[started_at] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_user_languages] UNIQUE NONCLUSTERED 
(
	[user_id] ASC,
	[language_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[user_lesson_progress] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_lesson_progress](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[lesson_id] [bigint] NOT NULL,
	[status] [varchar](20) NOT NULL,
	[completion_percentage] [decimal](5, 2) NOT NULL,
	[score] [decimal](5, 2) NULL,
	[started_at] [datetime2](7) NULL,
	[completed_at] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_user_lesson_progress] UNIQUE NONCLUSTERED 
(
	[user_id] ASC,
	[lesson_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[user_notification_settings] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_notification_settings](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[daily_reminder] [bit] NOT NULL,
	[weekly_report] [bit] NOT NULL,
	[review_reminder] [bit] NOT NULL,
	[ai_notifications] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_notification_settings_user] UNIQUE NONCLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[user_preferences] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_preferences](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[daily_goal_minutes] [int] NOT NULL,
	[preferred_learning_time] [time](7) NULL,
	[created_at] [datetime2](7) NOT NULL,
	[updated_at] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_user_preferences_user] UNIQUE NONCLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[user_roles] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_roles](
	[user_id] [bigint] NOT NULL,
	[role_id] [bigint] NOT NULL,
 CONSTRAINT [PK_user_roles] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[user_skill_progress] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_skill_progress](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[language_id] [bigint] NOT NULL,
	[skill_id] [bigint] NOT NULL,
	[level_id] [bigint] NOT NULL,
	[score] [decimal](5, 2) NOT NULL,
	[updated_at] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_user_skill_progress] UNIQUE NONCLUSTERED 
(
	[user_id] ASC,
	[language_id] ASC,
	[skill_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[user_vocabulary] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_vocabulary](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[vocabulary_id] [bigint] NOT NULL,
	[mastery_level] [int] NOT NULL,
	[correct_count] [int] NOT NULL,
	[wrong_count] [int] NOT NULL,
	[last_reviewed_at] [datetime2](7) NULL,
	[next_review_at] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_user_vocabulary] UNIQUE NONCLUSTERED 
(
	[user_id] ASC,
	[vocabulary_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[users] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](255) NOT NULL,
	[password_hash] [nvarchar](255) NOT NULL,
	[first_name] [nvarchar](100) NULL,
	[last_name] [nvarchar](100) NULL,
	[native_language_id] [bigint] NULL,
	[status] [varchar](20) NOT NULL,
	[created_at] [datetime2](7) NOT NULL,
	[updated_at] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ_users_email] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Objet : Table [dbo].[vocabulary] Date de script : 20/09/2026 18:50:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[vocabulary](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[language_id] [bigint] NOT NULL,
	[word] [nvarchar](255) NOT NULL,
	[translation] [nvarchar](255) NULL,
	[definition] [nvarchar](max) NULL,
	[example_sentence] [nvarchar](max) NULL,
	[audio_url] [nvarchar](1000) NULL,
	[difficulty] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Objet : Index [IX_courses_language] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_courses_language] ON [dbo].[courses]
(
	[language_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_courses_level] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_courses_level] ON [dbo].[courses]
(
	[level_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_exercise_options_exercise] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_exercise_options_exercise] ON [dbo].[exercise_options]
(
	[exercise_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_exercises_lesson] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_exercises_lesson] ON [dbo].[exercises]
(
	[lesson_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_lessons_module] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_lessons_module] ON [dbo].[lessons]
(
	[module_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_lessons_skill] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_lessons_skill] ON [dbo].[lessons]
(
	[skill_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_modules_course] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_modules_course] ON [dbo].[modules]
(
	[course_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_notifications_user] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_notifications_user] ON [dbo].[notifications]
(
	[user_id] ASC,
	[is_read] ASC,
	[created_at] DESC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_payments_subscription] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_payments_subscription] ON [dbo].[payments]
(
	[subscription_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_payments_user] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_payments_user] ON [dbo].[payments]
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [UX_subscription_usage_subscription_period] Date de script : 20/09/2026 18:50:30 ******/
CREATE UNIQUE NONCLUSTERED INDEX [UX_subscription_usage_subscription_period] ON [dbo].[subscription_usage]
(
	[subscription_id] ASC,
	[period_started_at] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Objet : Index [IX_subscriptions_status] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_subscriptions_status] ON [dbo].[subscriptions]
(
	[status] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_subscriptions_user] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_subscriptions_user] ON [dbo].[subscriptions]
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_attempts_exercise] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_attempts_exercise] ON [dbo].[user_exercise_attempts]
(
	[exercise_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_attempts_user] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_attempts_user] ON [dbo].[user_exercise_attempts]
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_user_languages_language] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_user_languages_language] ON [dbo].[user_languages]
(
	[language_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_user_languages_user_level] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_user_languages_user_level] ON [dbo].[user_languages]
(
	[user_id] ASC,
	[level_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_lesson_progress_lesson] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_lesson_progress_lesson] ON [dbo].[user_lesson_progress]
(
	[lesson_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_lesson_progress_user] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_lesson_progress_user] ON [dbo].[user_lesson_progress]
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_user_vocabulary_review] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_user_vocabulary_review] ON [dbo].[user_vocabulary]
(
	[user_id] ASC,
	[next_review_at] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_users_native_language] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_users_native_language] ON [dbo].[users]
(
	[native_language_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Objet : Index [IX_vocabulary_language] Date de script : 20/09/2026 18:50:30 ******/
CREATE NONCLUSTERED INDEX [IX_vocabulary_language] ON [dbo].[vocabulary]
(
	[language_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ai_assessments] ADD  DEFAULT (sysutcdatetime()) FOR [created_at]
GO
ALTER TABLE [dbo].[ai_conversations] ADD  DEFAULT (sysutcdatetime()) FOR [started_at]
GO
ALTER TABLE [dbo].[ai_messages] ADD  DEFAULT (sysutcdatetime()) FOR [created_at]
GO
ALTER TABLE [dbo].[conversation_feedback] ADD  DEFAULT (sysutcdatetime()) FOR [created_at]
GO
ALTER TABLE [dbo].[courses] ADD  DEFAULT ((1)) FOR [is_active]
GO
ALTER TABLE [dbo].[courses] ADD  DEFAULT (sysutcdatetime()) FOR [created_at]
GO
ALTER TABLE [dbo].[exercise_options] ADD  DEFAULT ((0)) FOR [is_correct]
GO
ALTER TABLE [dbo].[exercises] ADD  DEFAULT ((1)) FOR [difficulty]
GO
ALTER TABLE [dbo].[exercises] ADD  DEFAULT ((1)) FOR [points]
GO
ALTER TABLE [dbo].[exercises] ADD  DEFAULT (sysutcdatetime()) FOR [created_at]
GO
ALTER TABLE [dbo].[languages] ADD  DEFAULT ((1)) FOR [is_active]
GO
ALTER TABLE [dbo].[notifications] ADD  DEFAULT ((0)) FOR [is_read]
GO
ALTER TABLE [dbo].[notifications] ADD  DEFAULT (sysutcdatetime()) FOR [created_at]
GO
ALTER TABLE [dbo].[payments] ADD  DEFAULT (sysutcdatetime()) FOR [created_at]
GO
ALTER TABLE [dbo].[subscription_plans] ADD  DEFAULT ((1)) FOR [is_active]
GO
ALTER TABLE [dbo].[subscription_plans] ADD  CONSTRAINT [DF_subscription_plans_ads_enabled]  DEFAULT ((0)) FOR [ads_enabled]
GO
ALTER TABLE [dbo].[subscription_usage] ADD  DEFAULT ((0)) FOR [ai_messages_used]
GO
ALTER TABLE [dbo].[subscription_usage] ADD  DEFAULT ((0)) FOR [speaking_used]
GO
ALTER TABLE [dbo].[subscription_usage] ADD  DEFAULT (sysdatetime()) FOR [updated_at]
GO
ALTER TABLE [dbo].[subscriptions] ADD  DEFAULT ((1)) FOR [auto_renew]
GO
ALTER TABLE [dbo].[user_exercise_attempts] ADD  DEFAULT (sysutcdatetime()) FOR [attempted_at]
GO
ALTER TABLE [dbo].[user_languages] ADD  DEFAULT ((0)) FOR [is_primary]
GO
ALTER TABLE [dbo].[user_languages] ADD  DEFAULT (sysutcdatetime()) FOR [started_at]
GO
ALTER TABLE [dbo].[user_lesson_progress] ADD  DEFAULT ('NOT_STARTED') FOR [status]
GO
ALTER TABLE [dbo].[user_lesson_progress] ADD  DEFAULT ((0)) FOR [completion_percentage]
GO
ALTER TABLE [dbo].[user_notification_settings] ADD  DEFAULT ((1)) FOR [daily_reminder]
GO
ALTER TABLE [dbo].[user_notification_settings] ADD  DEFAULT ((1)) FOR [weekly_report]
GO
ALTER TABLE [dbo].[user_notification_settings] ADD  DEFAULT ((1)) FOR [review_reminder]
GO
ALTER TABLE [dbo].[user_notification_settings] ADD  DEFAULT ((1)) FOR [ai_notifications]
GO
ALTER TABLE [dbo].[user_preferences] ADD  DEFAULT ((15)) FOR [daily_goal_minutes]
GO
ALTER TABLE [dbo].[user_preferences] ADD  DEFAULT (sysutcdatetime()) FOR [created_at]
GO
ALTER TABLE [dbo].[user_skill_progress] ADD  DEFAULT ((0)) FOR [score]
GO
ALTER TABLE [dbo].[user_skill_progress] ADD  DEFAULT (sysutcdatetime()) FOR [updated_at]
GO
ALTER TABLE [dbo].[user_vocabulary] ADD  DEFAULT ((0)) FOR [mastery_level]
GO
ALTER TABLE [dbo].[user_vocabulary] ADD  DEFAULT ((0)) FOR [correct_count]
GO
ALTER TABLE [dbo].[user_vocabulary] ADD  DEFAULT ((0)) FOR [wrong_count]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT ('ACTIVE') FOR [status]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (sysutcdatetime()) FOR [created_at]
GO
ALTER TABLE [dbo].[vocabulary] ADD  DEFAULT ((1)) FOR [difficulty]
GO
ALTER TABLE [dbo].[ai_assessments]  WITH CHECK ADD  CONSTRAINT [FK_ai_assessments_conversation] FOREIGN KEY([conversation_id])
REFERENCES [dbo].[ai_conversations] ([id])
GO
ALTER TABLE [dbo].[ai_assessments] CHECK CONSTRAINT [FK_ai_assessments_conversation]
GO
ALTER TABLE [dbo].[ai_assessments]  WITH CHECK ADD  CONSTRAINT [FK_ai_assessments_level] FOREIGN KEY([level_id])
REFERENCES [dbo].[levels] ([id])
GO
ALTER TABLE [dbo].[ai_assessments] CHECK CONSTRAINT [FK_ai_assessments_level]
GO
ALTER TABLE [dbo].[ai_assessments]  WITH CHECK ADD  CONSTRAINT [FK_ai_assessments_skill] FOREIGN KEY([skill_id])
REFERENCES [dbo].[skills] ([id])
GO
ALTER TABLE [dbo].[ai_assessments] CHECK CONSTRAINT [FK_ai_assessments_skill]
GO
ALTER TABLE [dbo].[ai_assessments]  WITH CHECK ADD  CONSTRAINT [FK_ai_assessments_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[ai_assessments] CHECK CONSTRAINT [FK_ai_assessments_user]
GO
ALTER TABLE [dbo].[ai_conversations]  WITH CHECK ADD  CONSTRAINT [FK_ai_conversations_language] FOREIGN KEY([language_id])
REFERENCES [dbo].[languages] ([id])
GO
ALTER TABLE [dbo].[ai_conversations] CHECK CONSTRAINT [FK_ai_conversations_language]
GO
ALTER TABLE [dbo].[ai_conversations]  WITH CHECK ADD  CONSTRAINT [FK_ai_conversations_level] FOREIGN KEY([level_id])
REFERENCES [dbo].[levels] ([id])
GO
ALTER TABLE [dbo].[ai_conversations] CHECK CONSTRAINT [FK_ai_conversations_level]
GO
ALTER TABLE [dbo].[ai_conversations]  WITH CHECK ADD  CONSTRAINT [FK_ai_conversations_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[ai_conversations] CHECK CONSTRAINT [FK_ai_conversations_user]
GO
ALTER TABLE [dbo].[ai_messages]  WITH CHECK ADD  CONSTRAINT [FK_ai_messages_conversation] FOREIGN KEY([conversation_id])
REFERENCES [dbo].[ai_conversations] ([id])
GO
ALTER TABLE [dbo].[ai_messages] CHECK CONSTRAINT [FK_ai_messages_conversation]
GO
ALTER TABLE [dbo].[conversation_feedback]  WITH CHECK ADD  CONSTRAINT [FK_feedback_message] FOREIGN KEY([message_id])
REFERENCES [dbo].[ai_messages] ([id])
GO
ALTER TABLE [dbo].[conversation_feedback] CHECK CONSTRAINT [FK_feedback_message]
GO
ALTER TABLE [dbo].[courses]  WITH CHECK ADD  CONSTRAINT [FK_courses_language] FOREIGN KEY([language_id])
REFERENCES [dbo].[languages] ([id])
GO
ALTER TABLE [dbo].[courses] CHECK CONSTRAINT [FK_courses_language]
GO
ALTER TABLE [dbo].[courses]  WITH CHECK ADD  CONSTRAINT [FK_courses_level] FOREIGN KEY([level_id])
REFERENCES [dbo].[levels] ([id])
GO
ALTER TABLE [dbo].[courses] CHECK CONSTRAINT [FK_courses_level]
GO
ALTER TABLE [dbo].[exercise_options]  WITH CHECK ADD  CONSTRAINT [FK_exercise_options_exercise] FOREIGN KEY([exercise_id])
REFERENCES [dbo].[exercises] ([id])
GO
ALTER TABLE [dbo].[exercise_options] CHECK CONSTRAINT [FK_exercise_options_exercise]
GO
ALTER TABLE [dbo].[exercises]  WITH CHECK ADD  CONSTRAINT [FK_exercises_lesson] FOREIGN KEY([lesson_id])
REFERENCES [dbo].[lessons] ([id])
GO
ALTER TABLE [dbo].[exercises] CHECK CONSTRAINT [FK_exercises_lesson]
GO
ALTER TABLE [dbo].[lesson_sections]  WITH CHECK ADD  CONSTRAINT [FK_lesson_sections_lesson] FOREIGN KEY([lesson_id])
REFERENCES [dbo].[lessons] ([id])
GO
ALTER TABLE [dbo].[lesson_sections] CHECK CONSTRAINT [FK_lesson_sections_lesson]
GO
ALTER TABLE [dbo].[lessons]  WITH CHECK ADD  CONSTRAINT [FK_lessons_module] FOREIGN KEY([module_id])
REFERENCES [dbo].[modules] ([id])
GO
ALTER TABLE [dbo].[lessons] CHECK CONSTRAINT [FK_lessons_module]
GO
ALTER TABLE [dbo].[lessons]  WITH CHECK ADD  CONSTRAINT [FK_lessons_skill] FOREIGN KEY([skill_id])
REFERENCES [dbo].[skills] ([id])
GO
ALTER TABLE [dbo].[lessons] CHECK CONSTRAINT [FK_lessons_skill]
GO
ALTER TABLE [dbo].[modules]  WITH CHECK ADD  CONSTRAINT [FK_modules_course] FOREIGN KEY([course_id])
REFERENCES [dbo].[courses] ([id])
GO
ALTER TABLE [dbo].[modules] CHECK CONSTRAINT [FK_modules_course]
GO
ALTER TABLE [dbo].[notifications]  WITH CHECK ADD  CONSTRAINT [FK_notifications_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[notifications] CHECK CONSTRAINT [FK_notifications_user]
GO
ALTER TABLE [dbo].[payments]  WITH CHECK ADD  CONSTRAINT [FK_payments_subscription] FOREIGN KEY([subscription_id])
REFERENCES [dbo].[subscriptions] ([id])
GO
ALTER TABLE [dbo].[payments] CHECK CONSTRAINT [FK_payments_subscription]
GO
ALTER TABLE [dbo].[payments]  WITH CHECK ADD  CONSTRAINT [FK_payments_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[payments] CHECK CONSTRAINT [FK_payments_user]
GO
ALTER TABLE [dbo].[subscription_usage]  WITH CHECK ADD  CONSTRAINT [FK_subscription_usage_subscription] FOREIGN KEY([subscription_id])
REFERENCES [dbo].[subscriptions] ([id])
GO
ALTER TABLE [dbo].[subscription_usage] CHECK CONSTRAINT [FK_subscription_usage_subscription]
GO
ALTER TABLE [dbo].[subscriptions]  WITH CHECK ADD  CONSTRAINT [FK_subscriptions_plan] FOREIGN KEY([plan_id])
REFERENCES [dbo].[subscription_plans] ([id])
GO
ALTER TABLE [dbo].[subscriptions] CHECK CONSTRAINT [FK_subscriptions_plan]
GO
ALTER TABLE [dbo].[subscriptions]  WITH CHECK ADD  CONSTRAINT [FK_subscriptions_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[subscriptions] CHECK CONSTRAINT [FK_subscriptions_user]
GO
ALTER TABLE [dbo].[user_exercise_attempts]  WITH CHECK ADD  CONSTRAINT [FK_attempts_exercise] FOREIGN KEY([exercise_id])
REFERENCES [dbo].[exercises] ([id])
GO
ALTER TABLE [dbo].[user_exercise_attempts] CHECK CONSTRAINT [FK_attempts_exercise]
GO
ALTER TABLE [dbo].[user_exercise_attempts]  WITH CHECK ADD  CONSTRAINT [FK_attempts_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_exercise_attempts] CHECK CONSTRAINT [FK_attempts_user]
GO
ALTER TABLE [dbo].[user_languages]  WITH CHECK ADD  CONSTRAINT [FK_user_languages_language] FOREIGN KEY([language_id])
REFERENCES [dbo].[languages] ([id])
GO
ALTER TABLE [dbo].[user_languages] CHECK CONSTRAINT [FK_user_languages_language]
GO
ALTER TABLE [dbo].[user_languages]  WITH CHECK ADD  CONSTRAINT [FK_user_languages_level] FOREIGN KEY([level_id])
REFERENCES [dbo].[levels] ([id])
GO
ALTER TABLE [dbo].[user_languages] CHECK CONSTRAINT [FK_user_languages_level]
GO
ALTER TABLE [dbo].[user_languages]  WITH CHECK ADD  CONSTRAINT [FK_user_languages_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_languages] CHECK CONSTRAINT [FK_user_languages_user]
GO
ALTER TABLE [dbo].[user_lesson_progress]  WITH CHECK ADD  CONSTRAINT [FK_user_lesson_progress_lesson] FOREIGN KEY([lesson_id])
REFERENCES [dbo].[lessons] ([id])
GO
ALTER TABLE [dbo].[user_lesson_progress] CHECK CONSTRAINT [FK_user_lesson_progress_lesson]
GO
ALTER TABLE [dbo].[user_lesson_progress]  WITH CHECK ADD  CONSTRAINT [FK_user_lesson_progress_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_lesson_progress] CHECK CONSTRAINT [FK_user_lesson_progress_user]
GO
ALTER TABLE [dbo].[user_notification_settings]  WITH CHECK ADD  CONSTRAINT [FK_notification_settings_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_notification_settings] CHECK CONSTRAINT [FK_notification_settings_user]
GO
ALTER TABLE [dbo].[user_preferences]  WITH CHECK ADD  CONSTRAINT [FK_user_preferences_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_preferences] CHECK CONSTRAINT [FK_user_preferences_user]
GO
ALTER TABLE [dbo].[user_roles]  WITH CHECK ADD  CONSTRAINT [FK_user_roles_role] FOREIGN KEY([role_id])
REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE [dbo].[user_roles] CHECK CONSTRAINT [FK_user_roles_role]
GO
ALTER TABLE [dbo].[user_roles]  WITH CHECK ADD  CONSTRAINT [FK_user_roles_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_roles] CHECK CONSTRAINT [FK_user_roles_user]
GO
ALTER TABLE [dbo].[user_skill_progress]  WITH CHECK ADD  CONSTRAINT [FK_user_skill_language] FOREIGN KEY([language_id])
REFERENCES [dbo].[languages] ([id])
GO
ALTER TABLE [dbo].[user_skill_progress] CHECK CONSTRAINT [FK_user_skill_language]
GO
ALTER TABLE [dbo].[user_skill_progress]  WITH CHECK ADD  CONSTRAINT [FK_user_skill_level] FOREIGN KEY([level_id])
REFERENCES [dbo].[levels] ([id])
GO
ALTER TABLE [dbo].[user_skill_progress] CHECK CONSTRAINT [FK_user_skill_level]
GO
ALTER TABLE [dbo].[user_skill_progress]  WITH CHECK ADD  CONSTRAINT [FK_user_skill_skill] FOREIGN KEY([skill_id])
REFERENCES [dbo].[skills] ([id])
GO
ALTER TABLE [dbo].[user_skill_progress] CHECK CONSTRAINT [FK_user_skill_skill]
GO
ALTER TABLE [dbo].[user_skill_progress]  WITH CHECK ADD  CONSTRAINT [FK_user_skill_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_skill_progress] CHECK CONSTRAINT [FK_user_skill_user]
GO
ALTER TABLE [dbo].[user_vocabulary]  WITH CHECK ADD  CONSTRAINT [FK_user_vocabulary_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_vocabulary] CHECK CONSTRAINT [FK_user_vocabulary_user]
GO
ALTER TABLE [dbo].[user_vocabulary]  WITH CHECK ADD  CONSTRAINT [FK_user_vocabulary_vocabulary] FOREIGN KEY([vocabulary_id])
REFERENCES [dbo].[vocabulary] ([id])
GO
ALTER TABLE [dbo].[user_vocabulary] CHECK CONSTRAINT [FK_user_vocabulary_vocabulary]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FK_users_native_language] FOREIGN KEY([native_language_id])
REFERENCES [dbo].[languages] ([id])
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [FK_users_native_language]
GO
ALTER TABLE [dbo].[vocabulary]  WITH CHECK ADD  CONSTRAINT [FK_vocabulary_language] FOREIGN KEY([language_id])
REFERENCES [dbo].[languages] ([id])
GO
ALTER TABLE [dbo].[vocabulary] CHECK CONSTRAINT [FK_vocabulary_language]
GO
ALTER TABLE [dbo].[ai_assessments]  WITH CHECK ADD  CONSTRAINT [CK_ai_assessments_score] CHECK  (([score]>=(0) AND [score]<=(100)))
GO
ALTER TABLE [dbo].[ai_assessments] CHECK CONSTRAINT [CK_ai_assessments_score]
GO
ALTER TABLE [dbo].[ai_messages]  WITH CHECK ADD  CONSTRAINT [CK_ai_messages_role] CHECK  (([role]='ASSISTANT' OR [role]='USER' OR [role]='SYSTEM'))
GO
ALTER TABLE [dbo].[ai_messages] CHECK CONSTRAINT [CK_ai_messages_role]
GO
ALTER TABLE [dbo].[conversation_feedback]  WITH CHECK ADD  CONSTRAINT [CK_feedback_fluency] CHECK  (([fluency_score] IS NULL OR [fluency_score]>=(0) AND [fluency_score]<=(100)))
GO
ALTER TABLE [dbo].[conversation_feedback] CHECK CONSTRAINT [CK_feedback_fluency]
GO
ALTER TABLE [dbo].[conversation_feedback]  WITH CHECK ADD  CONSTRAINT [CK_feedback_grammar] CHECK  (([grammar_score] IS NULL OR [grammar_score]>=(0) AND [grammar_score]<=(100)))
GO
ALTER TABLE [dbo].[conversation_feedback] CHECK CONSTRAINT [CK_feedback_grammar]
GO
ALTER TABLE [dbo].[conversation_feedback]  WITH CHECK ADD  CONSTRAINT [CK_feedback_pronunciation] CHECK  (([pronunciation_score] IS NULL OR [pronunciation_score]>=(0) AND [pronunciation_score]<=(100)))
GO
ALTER TABLE [dbo].[conversation_feedback] CHECK CONSTRAINT [CK_feedback_pronunciation]
GO
ALTER TABLE [dbo].[conversation_feedback]  WITH CHECK ADD  CONSTRAINT [CK_feedback_vocabulary] CHECK  (([vocabulary_score] IS NULL OR [vocabulary_score]>=(0) AND [vocabulary_score]<=(100)))
GO
ALTER TABLE [dbo].[conversation_feedback] CHECK CONSTRAINT [CK_feedback_vocabulary]
GO
ALTER TABLE [dbo].[exercise_options]  WITH CHECK ADD  CONSTRAINT [CK_exercise_options_sequence] CHECK  (([sequence]>(0)))
GO
ALTER TABLE [dbo].[exercise_options] CHECK CONSTRAINT [CK_exercise_options_sequence]
GO
ALTER TABLE [dbo].[exercises]  WITH CHECK ADD  CONSTRAINT [CK_exercises_difficulty] CHECK  (([difficulty]>=(1) AND [difficulty]<=(5)))
GO
ALTER TABLE [dbo].[exercises] CHECK CONSTRAINT [CK_exercises_difficulty]
GO
ALTER TABLE [dbo].[exercises]  WITH CHECK ADD  CONSTRAINT [CK_exercises_points] CHECK  (([points]>(0)))
GO
ALTER TABLE [dbo].[exercises] CHECK CONSTRAINT [CK_exercises_points]
GO
ALTER TABLE [dbo].[exercises]  WITH CHECK ADD  CONSTRAINT [CK_exercises_type] CHECK  (([type]='FREE_TEXT' OR [type]='MULTIPLE_CHOICE' OR [type]='WRITING' OR [type]='FILL_BLANK'))
GO
ALTER TABLE [dbo].[exercises] CHECK CONSTRAINT [CK_exercises_type]
GO
ALTER TABLE [dbo].[lessons]  WITH CHECK ADD  CONSTRAINT [CK_lessons_duration] CHECK  (([estimated_minutes] IS NULL OR [estimated_minutes]>(0)))
GO
ALTER TABLE [dbo].[lessons] CHECK CONSTRAINT [CK_lessons_duration]
GO
ALTER TABLE [dbo].[lessons]  WITH CHECK ADD  CONSTRAINT [CK_lessons_sequence] CHECK  (([sequence]>(0)))
GO
ALTER TABLE [dbo].[lessons] CHECK CONSTRAINT [CK_lessons_sequence]
GO
ALTER TABLE [dbo].[modules]  WITH CHECK ADD  CONSTRAINT [CK_modules_sequence] CHECK  (([sequence]>(0)))
GO
ALTER TABLE [dbo].[modules] CHECK CONSTRAINT [CK_modules_sequence]
GO
ALTER TABLE [dbo].[payments]  WITH CHECK ADD  CONSTRAINT [CK_payments_amount] CHECK  (([amount]>=(0)))
GO
ALTER TABLE [dbo].[payments] CHECK CONSTRAINT [CK_payments_amount]
GO
ALTER TABLE [dbo].[payments]  WITH CHECK ADD  CONSTRAINT [CK_payments_status] CHECK  (([status]='REFUNDED' OR [status]='FAILED' OR [status]='SUCCESS' OR [status]='PENDING'))
GO
ALTER TABLE [dbo].[payments] CHECK CONSTRAINT [CK_payments_status]
GO
ALTER TABLE [dbo].[subscription_plans]  WITH CHECK ADD  CONSTRAINT [CK_subscription_plans_period] CHECK  (([billing_period]='YEARLY' OR [billing_period]='MONTHLY' OR [billing_period]='FREE'))
GO
ALTER TABLE [dbo].[subscription_plans] CHECK CONSTRAINT [CK_subscription_plans_period]
GO
ALTER TABLE [dbo].[subscription_plans]  WITH CHECK ADD  CONSTRAINT [CK_subscription_plans_price] CHECK  (([price]>=(0)))
GO
ALTER TABLE [dbo].[subscription_plans] CHECK CONSTRAINT [CK_subscription_plans_price]
GO
ALTER TABLE [dbo].[subscription_usage]  WITH CHECK ADD  CONSTRAINT [CK_subscription_usage_ai_messages_used] CHECK  (([ai_messages_used]>=(0)))
GO
ALTER TABLE [dbo].[subscription_usage] CHECK CONSTRAINT [CK_subscription_usage_ai_messages_used]
GO
ALTER TABLE [dbo].[subscription_usage]  WITH CHECK ADD  CONSTRAINT [CK_subscription_usage_speaking_used] CHECK  (([speaking_used]>=(0)))
GO
ALTER TABLE [dbo].[subscription_usage] CHECK CONSTRAINT [CK_subscription_usage_speaking_used]
GO
ALTER TABLE [dbo].[subscriptions]  WITH CHECK ADD  CONSTRAINT [CK_subscriptions_status] CHECK  (([status]='PAST_DUE' OR [status]='EXPIRED' OR [status]='CANCELLED' OR [status]='ACTIVE'))
GO
ALTER TABLE [dbo].[subscriptions] CHECK CONSTRAINT [CK_subscriptions_status]
GO
ALTER TABLE [dbo].[user_exercise_attempts]  WITH CHECK ADD  CONSTRAINT [CK_attempts_score] CHECK  (([score] IS NULL OR [score]>=(0) AND [score]<=(100)))
GO
ALTER TABLE [dbo].[user_exercise_attempts] CHECK CONSTRAINT [CK_attempts_score]
GO
ALTER TABLE [dbo].[user_lesson_progress]  WITH CHECK ADD  CONSTRAINT [CK_user_lesson_completion] CHECK  (([completion_percentage]>=(0) AND [completion_percentage]<=(100)))
GO
ALTER TABLE [dbo].[user_lesson_progress] CHECK CONSTRAINT [CK_user_lesson_completion]
GO
ALTER TABLE [dbo].[user_lesson_progress]  WITH CHECK ADD  CONSTRAINT [CK_user_lesson_score] CHECK  (([score] IS NULL OR [score]>=(0) AND [score]<=(100)))
GO
ALTER TABLE [dbo].[user_lesson_progress] CHECK CONSTRAINT [CK_user_lesson_score]
GO
ALTER TABLE [dbo].[user_lesson_progress]  WITH CHECK ADD  CONSTRAINT [CK_user_lesson_status] CHECK  (([status]='COMPLETED' OR [status]='IN_PROGRESS' OR [status]='NOT_STARTED'))
GO
ALTER TABLE [dbo].[user_lesson_progress] CHECK CONSTRAINT [CK_user_lesson_status]
GO
ALTER TABLE [dbo].[user_preferences]  WITH CHECK ADD  CONSTRAINT [CK_user_preferences_goal] CHECK  (([daily_goal_minutes]>(0)))
GO
ALTER TABLE [dbo].[user_preferences] CHECK CONSTRAINT [CK_user_preferences_goal]
GO
ALTER TABLE [dbo].[user_skill_progress]  WITH CHECK ADD  CONSTRAINT [CK_user_skill_score] CHECK  (([score]>=(0) AND [score]<=(100)))
GO
ALTER TABLE [dbo].[user_skill_progress] CHECK CONSTRAINT [CK_user_skill_score]
GO
ALTER TABLE [dbo].[user_vocabulary]  WITH CHECK ADD  CONSTRAINT [CK_user_vocabulary_correct] CHECK  (([correct_count]>=(0)))
GO
ALTER TABLE [dbo].[user_vocabulary] CHECK CONSTRAINT [CK_user_vocabulary_correct]
GO
ALTER TABLE [dbo].[user_vocabulary]  WITH CHECK ADD  CONSTRAINT [CK_user_vocabulary_mastery] CHECK  (([mastery_level]>=(0) AND [mastery_level]<=(5)))
GO
ALTER TABLE [dbo].[user_vocabulary] CHECK CONSTRAINT [CK_user_vocabulary_mastery]
GO
ALTER TABLE [dbo].[user_vocabulary]  WITH CHECK ADD  CONSTRAINT [CK_user_vocabulary_wrong] CHECK  (([wrong_count]>=(0)))
GO
ALTER TABLE [dbo].[user_vocabulary] CHECK CONSTRAINT [CK_user_vocabulary_wrong]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [CK_users_status] CHECK  (([status]='SUSPENDED' OR [status]='INACTIVE' OR [status]='ACTIVE'))
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [CK_users_status]
GO
ALTER TABLE [dbo].[vocabulary]  WITH CHECK ADD  CONSTRAINT [CK_vocabulary_difficulty] CHECK  (([difficulty]>=(1) AND [difficulty]<=(5)))
GO
ALTER TABLE [dbo].[vocabulary] CHECK CONSTRAINT [CK_vocabulary_difficulty]
GO

